package com.example.demo2.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION( 9999 ,"Uncategorized Exception"),
    USER_EXITED(1002, "User exited"),
    USER_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters"),
    USER_NOT_EXISTED(1005, "User does not existed"),

            ;


    ErrorCode (int code,String message){
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;
}
