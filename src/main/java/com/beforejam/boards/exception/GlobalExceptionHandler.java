package com.beforejam.boards.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 프로젝트 전역의 컨트롤러에서 터지는 에러를 감시하는 레이터 방
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
