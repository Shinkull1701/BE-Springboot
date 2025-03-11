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
@RequestMapping("users")
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
    ApiResponse<UserRespone> getUser(@PathVariable Long userId){

         return ApiResponse.<UserRespone>builder()
                .result(userService.getUser(userId))
                .build();
    }
    @GetMapping("/myInfo")
    ApiResponse<UserRespone> getUser(){

        return ApiResponse.<UserRespone>builder()
                .result(userService.getMyinfo())
                .build();
    }


    @PutMapping("{userid}")
    ApiResponse<UserRespone> updateUser(@PathVariable Long userid, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserRespone>builder()
                .result(userService.updateUser(userid,request))
                .build();

    };


    @DeleteMapping("{userid}")
    ApiResponse<String> deleteUser(@PathVariable Long userid){
        userService.deleteUser(userid);
        return ApiResponse.<String>builder()
                .result("User has been delete")
                .build();
    };

}
