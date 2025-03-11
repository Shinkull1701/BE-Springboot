package com.example.demo2.dto.response;

import com.example.demo2.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRespone {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    Set<Role> roles;
}
