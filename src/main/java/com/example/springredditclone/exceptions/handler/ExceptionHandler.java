package com.example.springredditclone.exceptions.handler;

import com.example.springredditclone.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({MailException.class})
    public ResponseEntity<?> handleMailException(NotFoundException notFoundException){
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({NotActivatedException.class, JwtException.class})
    public ResponseEntity<?> handleNotActivated(NotActivatedException notActivatedException){
        return new ResponseEntity<>(notActivatedException.getMessage(), HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({WordException.class})
    public ResponseEntity<?> handleWordException(WordException wordException){
        return new ResponseEntity<>(wordException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({FoundException.class})
    public ResponseEntity<?> handleExistException(FoundException foundException){
        return new ResponseEntity<>(foundException.getMessage(), HttpStatus.FOUND);
    }

}
