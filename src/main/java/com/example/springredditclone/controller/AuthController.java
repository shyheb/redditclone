package com.example.springredditclone.controller;

import com.example.springredditclone.payload.request.RegistraterRequest;
import com.example.springredditclone.payload.request.SignInRequest;
import com.example.springredditclone.payload.response.JwtResponse;
import com.example.springredditclone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody RegistraterRequest registraterRequest) {
        authService.signUp(registraterRequest);
        return new ResponseEntity<>("User registration successful", HttpStatus.CREATED);
    }

    @PutMapping("/verifyAccount/{token}")
    public ResponseEntity<?> enableAccount(@PathVariable String token){
        authService.enableAccount(token);
        return new ResponseEntity<>("Account Activated suucceful", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest){
        String token = authService.signIn(signInRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);

    }
}
