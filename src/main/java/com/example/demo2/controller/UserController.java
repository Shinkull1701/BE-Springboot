package com.example.demo2.controller;

import com.example.demo2.Service.UserService;
import com.example.demo2.dto.response.ApiResponse;
import com.example.demo2.dto.request.UserCreationRequest;
import com.example.demo2.Entity.User;
import com.example.demo2.dto.request.UserUpdateRequest;
import com.example.demo2.dto.response.UserRespone;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

   UserService userService;

    @PostMapping("users")
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest  request){
        ApiResponse<User> response = new ApiResponse<>();
        response.setResult(userService.createUser(request));
        return response;

    }
    @GetMapping("listusers")
    List<User> listUser(){
        return userService.getUser();
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
