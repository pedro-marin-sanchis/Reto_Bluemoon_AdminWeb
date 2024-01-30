package com.uguinformatica.bluemoon_adminweb.service.user;

import com.uguinformatica.bluemoon_adminweb.model.AppUser;
import com.uguinformatica.bluemoon_adminweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = userRepository.findByUsername(username);
        if (appUser.isEmpty()) throw new UsernameNotFoundException("Username not found.");
        return new CustomUserDetails(appUser.get());
    }
}