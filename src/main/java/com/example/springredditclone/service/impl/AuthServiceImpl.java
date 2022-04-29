package com.example.springredditclone.service.impl;

import com.example.springredditclone.config.AppConfig;
import com.example.springredditclone.exceptions.NotFoundException;
import com.example.springredditclone.model.*;
import com.example.springredditclone.payload.request.RegistraterRequest;
import com.example.springredditclone.payload.request.SignInRequest;
import com.example.springredditclone.payload.request.SignInResponse;
import com.example.springredditclone.repository.RoleRepository;
import com.example.springredditclone.repository.UserRepository;
import com.example.springredditclone.repository.VerificationTokenRepository;
import com.example.springredditclone.security.JwtUtils;
import com.example.springredditclone.service.AuthService;
import com.example.springredditclone.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AppConfig appConfig;

    @Override
    @Transactional
    public void signUp(RegistraterRequest registraterRequest) {

        if (userRepository.findByEmail(registraterRequest.getEmail()).isPresent()) {
            throw new NotFoundException("User Email Exist");
        }

        User user = new User();
        user.setEmail(registraterRequest.getEmail());
        user.setUsername(registraterRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registraterRequest.getPassword()));
        user.setCreatedDAte(String.valueOf(Instant.now()));
        user.setEnabled(false);

        Set<ERole> roles = registraterRequest.getRoles();
        if (roles == null || roles.isEmpty()) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(
                    () -> new NotFoundException("ROLE: " + ERole.ROLE_USER + " NOT FOUND"));
            user.getRoles().add(userRole);
        }else {
            roles.forEach(
                    eRole -> {
                        switch (eRole) {
                            case ROLE_USER:
                                Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(
                                        () -> new NotFoundException("ROLE: " + ERole.ROLE_USER + " NOT FOUND"));
                                user.getRoles().add(userRole);
                                break;

                            case ROLE_ADMIN:
                                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(
                                        () -> new NotFoundException("ROLE: " + ERole.ROLE_ADMIN + " NOT FOUND"));
                                user.getRoles().add(adminRole);
                                break;

                            case ROLE_MODERATOR:
                                Role moderatorRole = roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(
                                        () -> new NotFoundException("ROLE: " + ERole.ROLE_MODERATOR + " NOT FOUND"));
                                user.getRoles().add(moderatorRole);
                                break;
                        }
                    }
            );
        }
        userRepository.save(user);

        String token = generateVerificationToken(user);

        mailService.sendEmail(new NotificationEmail(
                "Activation Account",
                user.getEmail(),
                "Thank you for sign up <br/> Please click here to activate you Email : <a href='"+appConfig.getAppUrl()+"'/api/auth/accountVerification/\" + token'> http://localhost:8080/api/auth/accountVerification/" + token + "</a>"
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
                () -> new NotFoundException("Token Not Found" + token));
        activateAccount(verificationToken);
    }

    private void activateAccount(VerificationToken verificationToken) {
        User user = userRepository.findById(verificationToken.getUser().getId()).orElseThrow(
                () -> new NotFoundException("User Not Found"));
        user.setEnabled(true);
        userRepository.save(user);
    }


    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return SignInResponse.builder().token(jwt).email(userDetails.getEmail()).build();
    }
}
