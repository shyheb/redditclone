package com.example.springredditclone.security;

import com.example.springredditclone.exceptions.JwtException;
import com.example.springredditclone.exceptions.NotFoundException;
import com.example.springredditclone.model.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secretCode}")
    private String jwtSecretCode;

    @Value("${jwt.expirationMs}")
    private int expirationMs;

    public String generateToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecretCode)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretCode).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean valiateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretCode).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            throw new JwtException("Invalid Jwt Signature " + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new JwtException("Malformed Jwt " + e.getMessage());
        } catch (ExpiredJwtException e) {
            throw new JwtException("Expired Jwt " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new JwtException("unsupported Jwt " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new JwtException("illegalArgument Jwt " + e.getMessage());
        }
    }
}
