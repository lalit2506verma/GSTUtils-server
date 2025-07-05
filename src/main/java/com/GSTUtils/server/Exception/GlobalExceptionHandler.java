package com.GSTUtils.server.Exception;

import com.GSTUtils.server.Model.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleAllException(Exception e){
        return ResponseEntity
                .badRequest()
                .body(new GenericResponse("error", e.getMessage()));
    }
}
