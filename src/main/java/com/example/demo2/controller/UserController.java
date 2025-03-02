package com.example.demo2.controller;

import com.example.demo2.Service.UserService;
import com.example.demo2.request.ApiResponse;
import com.example.demo2.request.UserCreationRequest;
import com.example.demo2.model.User;
import com.example.demo2.request.UserUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("users")
    ApiResponse<User> creatUser(@RequestBody @Valid UserCreationRequest  request){
        ApiResponse<User> response = new ApiResponse<>();
        response.setResuilt(userService.createUser(request));
        return response;

    }
    @GetMapping("listusers")
    List<User> listUser(){
        return userService.getUser();
    }
    @GetMapping("{userid}")
    User getUser(@PathVariable Long userid){
        return userService.getUser(userid);
    }
    @PutMapping("{userid}")
    User updateUser(@PathVariable Long userid, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userid, request);
    };
    @DeleteMapping("{userid}")
    String deleteUser(@PathVariable Long userid){
        userService.deleteUser(userid);
        return "User has been deleted";
    };

}
