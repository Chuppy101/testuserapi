package com.example.testuserapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank
    private String fio;

    @Pattern(regexp = "\\+?\\d{10,15}")
    private String phoneNumber;

    @org.hibernate.validator.constraints.URL
    private String avatar;

    @NotBlank
    private String roleName;
}
