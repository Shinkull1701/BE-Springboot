package com.example.demo2.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION( 9999 ,"Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR ),
    USER_EXITED(1002, "User exited",HttpStatus.BAD_REQUEST),
    USER_INVALID(1003, "Username must be at least 3 characters",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User does not existed",HttpStatus.NOT_FOUND),
    AUTHENTICATION_FAILED(1006, "Authentication failed",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permisstion",HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(1008, "Authentication failed",HttpStatus.UNAUTHORIZED),
            ;


    ErrorCode (int code,String message,HttpStatusCode statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
