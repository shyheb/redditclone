package com.example.springredditclone.service;

import com.example.springredditclone.payload.request.RegistraterRequest;
import com.example.springredditclone.payload.request.SignInRequest;
import com.example.springredditclone.payload.request.SignInResponse;

public interface AuthService {
    void signUp(RegistraterRequest registraterRequest);
    void enableAccount(String token);
    SignInResponse signIn(SignInRequest signInRequest);
}
