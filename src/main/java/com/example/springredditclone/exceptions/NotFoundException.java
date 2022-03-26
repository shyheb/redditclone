package com.example.springredditclone.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String exMessage) {
        super(exMessage);
    }
}
