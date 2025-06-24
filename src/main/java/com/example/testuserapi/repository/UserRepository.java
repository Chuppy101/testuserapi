package com.example.testuserapi.repository;

import com.example.testuserapi.model.User;
import com.example.testuserapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    long countByRole(Role role);
}