package com.example.springredditclone.controller;

import com.example.springredditclone.dto.RegistraterRequest;
import com.example.springredditclone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegistraterRequest registraterRequest) {
        authService.signUp(registraterRequest);
        return new ResponseEntity<>("User registration successful", HttpStatus.CREATED);
    }

    @PutMapping("/verifyAccount/{token}")
    public ResponseEntity<?> enableAccount(@PathVariable String token){
        authService.enableAccount(token);
        return new ResponseEntity<>("Account Activated suucceful", HttpStatus.OK);
    }
}
