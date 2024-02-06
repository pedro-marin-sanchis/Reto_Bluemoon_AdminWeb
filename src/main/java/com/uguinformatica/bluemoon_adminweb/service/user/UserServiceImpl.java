package com.uguinformatica.bluemoon_adminweb.service.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uguinformatica.bluemoon_adminweb.model.User;

import com.uguinformatica.bluemoon_adminweb.service.APIConstants;
import com.uguinformatica.bluemoon_adminweb.service.auth.CustomAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<User> getUserByUsername(String username) {

        String token = getUserAuthToken("bluemoon_admin", "ur%]SEmRPcvMqfB;2xs>!");
        // HASH: $2y$10$mYRmCJmc5jcD2dniHqJISOfQRqTXCayVibX/t2kynDPyL0X0cUOpy

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate.exchange(
                APIConstants.API_URL + "/users/" + username,
                HttpMethod.GET,
                entity,
                User.class
        );

        return Optional.ofNullable(response.getBody());
    }

    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CustomAuthenticationToken) {
            CustomAuthenticationToken customAuthToken = (CustomAuthenticationToken) authentication;
            String username = customAuthToken.getName();
            String token = customAuthToken.getToken();
            Optional<User> user = getUserByUsername(username);
            user.get().setAuthToken(token);
            return user;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String getUserAuthToken(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(APIConstants.API_URL + "/login", requestEntity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            return root.get("token").asText();
        } catch(HttpClientErrorException e) {
            System.out.println("Login failed -> HTTP Response "+e.getMessage());
        } catch (IOException ignored) {}
        return null;
    }

    @Override
    public String getCurrentUserAuthToken() {
        return "a";
    }

}