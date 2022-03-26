package com.example.springredditclone.exceptions;

public class MailException extends RuntimeException {
    public MailException(String exMessage) {
        super(exMessage);
    }
}
