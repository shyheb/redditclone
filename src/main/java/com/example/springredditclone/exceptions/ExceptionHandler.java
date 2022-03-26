package com.example.springredditclone.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jdi.InternalException;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({SpringRedditException.class})
    public ResponseEntity<?> handleSpringRedditException(SpringRedditException springRedditException){
        return new ResponseEntity<>(springRedditException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleSpringRedditException(IllegalArgumentException illegalArgumentException){
        return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.NOT_FOUND);
    }

}
