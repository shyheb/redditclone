package com.example.springredditclone.service;

import com.example.springredditclone.payload.request.RegistraterRequest;
import com.example.springredditclone.payload.request.SignInRequest;

public interface AuthService {
    void signUp(RegistraterRequest registraterRequest);
    void enableAccount(String token);
    void signIn(SignInRequest signInRequest);
}
