package com.example.testuserapi.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;
    private String fio;
    private String phoneNumber;
    private String avatar;
    private String roleName;
}
