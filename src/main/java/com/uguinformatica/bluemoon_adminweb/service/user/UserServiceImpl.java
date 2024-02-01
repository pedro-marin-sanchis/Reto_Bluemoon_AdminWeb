package com.uguinformatica.bluemoon_adminweb.service.user;

import com.uguinformatica.bluemoon_adminweb.model.Role;
import com.uguinformatica.bluemoon_adminweb.model.User;

import com.uguinformatica.bluemoon_adminweb.service.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        Optional<User> user = Optional.ofNullable(restTemplate.getForObject(APIConstants.API_URL + "/users/" + username, User.class));
        return user;
    }

    @Override
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return getUserByUsername(username);
    }
}