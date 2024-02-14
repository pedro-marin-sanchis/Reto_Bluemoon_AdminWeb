package com.uguinformatica.bluemoon_adminweb.service.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class BluemoonAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String authenticationToken; // JSON Web Token

    public BluemoonAuthenticationToken(Object principal, Object authenticationToken, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.authenticationToken = (String) authenticationToken;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }
}