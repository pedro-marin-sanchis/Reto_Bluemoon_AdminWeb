package com.uguinformatica.bluemoon_adminweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String getLogin() {
        return "/auth/auth_login";
    }

    @GetMapping("/logout")
    public String getLogout() {
        return "/auth/auth_logout";
    }

}
