package com.example.demo2.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserCreationRequest {
    @Size(min=3, message = "USER_INVALID")
    private String username;
    @Size(min = 8,message = ("PASSWORD_INVALID"))
    private String password;
    @Email(message = "Invalid email")
    @Pattern(regexp = "^[A-Za-z0-9+_-]+@[A-Za-z0-9_-]+\\.com$")
    private String email;
    private String firstName;
    private String lastName;
}
