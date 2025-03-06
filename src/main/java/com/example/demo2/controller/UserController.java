package com.example.demo2.controller;

import com.example.demo2.service.UserService;
import com.example.demo2.dto.response.ApiResponse;
import com.example.demo2.dto.request.UserCreationRequest;
import com.example.demo2.entity.User;
import com.example.demo2.dto.request.UserUpdateRequest;
import com.example.demo2.dto.response.UserRespone;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
public class UserController {

   UserService userService;

    @PostMapping("createusers")
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest  request){
        ApiResponse<User> response = new ApiResponse<>();
        response.setResult(userService.createUser(request));
        return response;

    }

    @GetMapping("listusers")
    ApiResponse<List<UserRespone>>getUsers(){
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        log.info("Username {}",authentication.getName());
        authentication.getAuthorities().forEach(
                grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return ApiResponse.<List<UserRespone>>builder()
                .result(userService.getUsers())
                .build();
    }
    @GetMapping("{userid}")
    UserRespone getUser(@PathVariable Long userid){
        return userService.getUser(userid);
    }

    @PutMapping("{userid}")
    UserRespone updateUser(@PathVariable Long userid, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userid, request);
    };


    @DeleteMapping("{userid}")
    String deleteUser(@PathVariable Long userid){
        userService.deleteUser(userid);
        return "User has been deleted";
    };

}
