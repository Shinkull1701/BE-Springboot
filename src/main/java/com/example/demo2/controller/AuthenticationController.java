package com.example.demo2.controller;

import com.example.demo2.Service.AuthenticationService;
import com.example.demo2.dto.request.AuthenticationRequest;
import com.example.demo2.dto.response.ApiResponse;
import com.example.demo2.dto.response.AuthenticationResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

        @PostMapping("/log-in")
        ApiResponse<AuthenticationResponse>authentication(@RequestBody AuthenticationRequest request){
            boolean result= authenticationService.authenticate(request);
            return ApiResponse.<AuthenticationResponse>builder()
                    .result(AuthenticationResponse.builder()
                            .authenticated(result)
                            .build())
                    .build();

        }
}
