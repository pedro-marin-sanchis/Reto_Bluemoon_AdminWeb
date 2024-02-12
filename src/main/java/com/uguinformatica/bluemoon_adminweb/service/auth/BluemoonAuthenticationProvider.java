package com.uguinformatica.bluemoon_adminweb.service.auth;

import com.uguinformatica.bluemoon_adminweb.model.Role;
import com.uguinformatica.bluemoon_adminweb.model.User;
import com.uguinformatica.bluemoon_adminweb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class BluemoonAuthenticationProvider implements AuthenticationProvider {

    private final IUserService userService;

    @Autowired
    public BluemoonAuthenticationProvider(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String apiToken = userService.getUserAuthToken(username, password);

        if (apiToken != null) {
            System.out.println("Authentication Token Present");
            // Load user details from the user service.
            Optional<User> user = userService.getUserByUsername(username);
            if (user.isPresent()) {
                // User is authenticated, so assign roles.
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for (Role role : user.get().getRolesAssociated()) {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                }
                // Create a custom authentication token with username, token, password, and authorities.
                BluemoonAuthenticationToken authToken = new BluemoonAuthenticationToken(username, apiToken, password, authorities);
                System.out.println("Authentication Successful");
                return authToken;
            }
        }
        System.out.println("Authentication Failed");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}