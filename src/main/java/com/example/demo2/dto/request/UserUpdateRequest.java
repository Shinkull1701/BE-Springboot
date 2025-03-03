package com.example.demo2.dto.request;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
