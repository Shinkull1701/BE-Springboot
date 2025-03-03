package com.example.demo2.dto.response;

import lombok.Data;

@Data
public class UserRespone {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
