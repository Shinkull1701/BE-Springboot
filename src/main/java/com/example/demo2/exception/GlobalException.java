package com.example.demo2.exception;

import com.example.demo2.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = RuntimeException.class)
        ResponseEntity<ApiResponse>handlingRuntimeException(RuntimeException runtimeException){
            ApiResponse response = new ApiResponse();
            response.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
            response.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(response);

    };
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse>handlingRuntimeException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse response = new ApiResponse();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(response);
    };
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse>handlingValidationException(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        ApiResponse response = new ApiResponse();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(response );
    }
}
