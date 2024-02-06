package com.uguinformatica.bluemoon_adminweb.service.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String token;

    public CustomAuthenticationToken(Object principal, Object token, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.token = (String) token;
    }

    public String getToken() {
        return token;
    }
}