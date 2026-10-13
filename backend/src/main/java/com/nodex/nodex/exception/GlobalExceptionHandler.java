package com.nodex.nodex.exception;

import com.nodex.nodex.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleServerNotFound(ServerNotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponseDto body = new ErrorResponseDto(
                status.value(),
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(body);
    }
}
