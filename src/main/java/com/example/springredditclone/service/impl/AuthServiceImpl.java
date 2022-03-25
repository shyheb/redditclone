package com.example.springredditclone.service.impl;

import com.example.springredditclone.dto.RegistraterRequest;
import com.example.springredditclone.exceptions.SpringRedditException;
import com.example.springredditclone.model.NotificationEmail;
import com.example.springredditclone.model.User;
import com.example.springredditclone.model.VerificationToken;
import com.example.springredditclone.repository.UserRepository;
import com.example.springredditclone.repository.VerificationTokenRepository;
import com.example.springredditclone.service.AuthService;
import com.example.springredditclone.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Override
    @Transactional
    public void signUp(RegistraterRequest registraterRequest) {
        User user = new User();
        user.setEmail(registraterRequest.getEmail());
        user.setName(registraterRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registraterRequest.getPassword()));
        user.setCreatedDAte(String.valueOf(Instant.now()));
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);

        mailService.sendEmail(new NotificationEmail(
                "Activation Account",
                user.getEmail(),
                "Thank you for sign up <br/> Please click here to activate you Email : <a href='http://localhost:8080/api/auth/accountVerification/\" + token'> http://localhost:8080/api/auth/accountVerification/" + token + "</a>"
        ));

    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);

        return token;
    }

    @Override
    public void enableAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElseThrow(
                () ->  new SpringRedditException("Token Not Found" + token));
        activateAccount(verificationToken);
    }

    private void activateAccount(VerificationToken verificationToken) {
        User user = userRepository.findById(verificationToken.getUser().getId()).orElseThrow(
                () ->  new SpringRedditException("User Not Found"));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
