package com.example.springredditclone.service;

import com.example.springredditclone.dto.RegistraterRequest;

public interface AuthService {
    void signUp(RegistraterRequest registraterRequest);
    void enableAccount(String token);
}
