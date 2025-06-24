package com.example.testuserapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String fio;

    @Pattern(regexp = "\\+?\\d{10,15}")
    private String phoneNumber;

    @org.hibernate.validator.constraints.URL
    private String avatar;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Role role;
}
