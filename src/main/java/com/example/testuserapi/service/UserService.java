package com.example.testuserapi.service;   // <-- проверь, что путь совпадает с папкой

import com.example.testuserapi.model.Role;
import com.example.testuserapi.model.User;
import com.example.testuserapi.repository.RoleRepository;
import com.example.testuserapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository users;

    private final RoleRepository roles;

    /** СОЗДАТЬ -------------------------------------------------------- */
    public User create(User user) {
        // ищем роль по имени; если её нет — сохраняем новую
        Role role = roles.findByRoleName(user.getRole().getRoleName())
                .orElseGet(() -> roles.save(user.getRole()));

        user.setRole(role);
        return users.save(user);
    }

    /** ПОЛУЧИТЬ ------------------------------------------------------- */
    @Cacheable("users")
    public User get(UUID id) {
        return users.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    /** ОБНОВИТЬ ------------------------------------------------------- */
    public User update(UUID id, User updated) {
        User existing = get(id);

        existing.setFio(updated.getFio());
        existing.setPhoneNumber(updated.getPhoneNumber());
        existing.setAvatar(updated.getAvatar());

        Role role = roles.findByRoleName(updated.getRole().getRoleName())
                .orElseGet(() -> roles.save(updated.getRole()));
        existing.setRole(role);

        return users.save(existing);
    }

    /** УДАЛИТЬ -------------------------------------------------------- */
    public void delete(UUID id) {
        User existing = users.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Role role = existing.getRole();
        users.delete(existing);

        if (users.countByRole(role) == 0) {
            roles.delete(role);
        }
    }
}
