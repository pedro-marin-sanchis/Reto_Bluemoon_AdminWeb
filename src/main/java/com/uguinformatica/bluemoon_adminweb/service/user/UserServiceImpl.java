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
        //DEBUG:
        double balance = 9000;
        HashSet<Role> roles = new HashSet<Role>();
        Role role = new Role();
        role.setName("ADMIN");
        role.setId(0L);
        roles.add(role);
        User userDebug = new User("pmarins", "Pedro", "Marin Sanchís", "pmarins@bluemoon.com", "0894759083", "C/Calle N1", balance, "$2y$10$6aKyQdp44BvALjgeVXpkQeSqQ2sWGT56T0F6dIGM3IdgT2UKVCvS.", roles);
        return Optional.ofNullable(userDebug);
        //return Optional.ofNullable(restTemplate.getForObject(APIConstants.API_URL + "/user/" + username, User.class));
    }

    @Override
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return getUserByUsername(username);
    }
}