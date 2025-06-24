package com.example.testuserapi.controller;

import com.example.testuserapi.dto.*;
import com.example.testuserapi.model.*;
import com.example.testuserapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    /* --- 1. CREATE ------------------------------------ */
    @PostMapping("/createNewUser")
    public UserResponse create(@Valid @RequestBody UserRequest req) {
        User saved = service.create(toUser(req));
        return toResp(saved);
    }

    /* --- 2. READ -------------------------------------- */
    @GetMapping("/users")
    public UserResponse get(@RequestParam UUID userID) {
        return toResp(service.get(userID));
    }

    /* --- 3. UPDATE ------------------------------------ */
    @PutMapping("/userDetailsUpdate")
    public UserResponse update(@RequestParam UUID userID,
                               @Valid @RequestBody UserRequest req) {
        return toResp(service.update(userID, toUser(req)));
    }

    /* --- 4. DELETE ------------------------------------ */
    @DeleteMapping("/users")
    public void delete(@RequestParam UUID userID) {
        service.delete(userID);
    }


    private User toUser(UserRequest r) {
        return new User(
                null,
                r.getFio(),
                r.getPhoneNumber(),
                r.getAvatar(),
                new Role(null, r.getRoleName())
        );
    }

    private UserResponse toResp(User u) {
        UserResponse r = new UserResponse();
        r.setId(u.getId());
        r.setFio(u.getFio());
        r.setPhoneNumber(u.getPhoneNumber());
        r.setAvatar(u.getAvatar());
        r.setRoleName(u.getRole().getRoleName());
        return r;
    }
}
