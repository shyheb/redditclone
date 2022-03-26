package com.example.springredditclone.exceptions;

public class NotActivatedException extends RuntimeException {
    public NotActivatedException(String exMessage) {
        super(exMessage);
    }
}
