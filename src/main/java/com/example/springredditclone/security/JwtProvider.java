package com.example.springredditclone.security;

import com.example.springredditclone.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class JwtProvider {

    //calling auto after initialize all beans
    @PostConstruct
    public void init() {

    }

    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getEmail())
                .signWith(SignatureAlgorithm.HS256, "lm")
                .compact();
    }
}
