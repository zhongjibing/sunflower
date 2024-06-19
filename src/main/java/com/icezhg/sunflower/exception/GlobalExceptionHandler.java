package com.icezhg.sunflower.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by zhongjibing on 2023/06/20.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(HttpServletRequest request, HttpServletResponse response,
            Exception exception) {
        return new ResponseEntity<>("error", HttpStatusCode.valueOf(200));
    }
}
