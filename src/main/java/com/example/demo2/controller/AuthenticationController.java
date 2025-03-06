package com.example.demo2.controller;

import com.example.demo2.dto.request.IntrospectRequest;
import com.example.demo2.dto.response.IntrospectResponse;
import com.example.demo2.service.AuthenticationService;
import com.example.demo2.dto.request.AuthenticationRequest;
import com.example.demo2.dto.response.ApiResponse;
import com.example.demo2.dto.response.AuthenticationResponse;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class AuthenticationController {

    AuthenticationService authenticationService;

        @PostMapping("/token")
        ApiResponse<AuthenticationResponse>authentication(@RequestBody AuthenticationRequest request){
            var result= authenticationService.authenticate(request);
            return ApiResponse.<AuthenticationResponse>builder()
                    .result(result)
                    .build();

        }
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse>authentication(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
            var result= authenticationService.introspec(request);
            return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();

    }
}
