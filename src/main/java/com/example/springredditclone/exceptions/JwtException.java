package com.example.springredditclone.exceptions;

public class JwtException  extends RuntimeException {
    public JwtException(String exMessage) {
        super(exMessage);
    }
}
