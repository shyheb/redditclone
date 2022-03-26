package com.example.springredditclone.service.impl;

import com.example.springredditclone.exceptions.SpringRedditException;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new SpringRedditException("User Not Found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                getAuthorities("USER")
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
}
