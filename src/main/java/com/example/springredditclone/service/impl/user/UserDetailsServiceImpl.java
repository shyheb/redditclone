package com.example.springredditclone.service.impl.user;

import com.example.springredditclone.exceptions.NotActivatedException;
import com.example.springredditclone.exceptions.NotFoundException;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new NotFoundException("User Not Found"));

        if (!user.isEnabled()){
            throw new NotActivatedException("User Account not activated");
        }

        return user;
    }

}
