package com.uguinformatica.bluemoon_adminweb.controller;

import com.uguinformatica.bluemoon_adminweb.model.AppUser;
import com.uguinformatica.bluemoon_adminweb.model.Role;
import com.uguinformatica.bluemoon_adminweb.service.role.IRoleService;
import com.uguinformatica.bluemoon_adminweb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final IUserService userService;
    private final IRoleService roleService;

    @Autowired
    public AuthController(IUserService userService, IRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "/auth/auth_login";
    }

    @GetMapping("/logout")
    public String getLogout() {
        return "/auth/auth_logout";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "/auth/auth_register";
    }


    @PostMapping("/register")
    public String postRegister(
            @RequestParam("username") String username,
            @RequestParam("name") String name,
            @RequestParam("lastname") String lastname,
            @RequestParam("email") String email,
            @RequestParam("phonenumber") String phonenumber,
            @RequestParam("password") String password,
            Model model
    ) {
        System.out.println("Working on it");
        try {
            Set<Role> rolesAssociated = new HashSet<>();
            Optional<Role> role = roleService.getRoleByName("USER");
            role.ifPresent(rolesAssociated::add);

            AppUser newAppUser = new AppUser(username, name, lastname, email, phonenumber, password, rolesAssociated);
            userService.createUser(newAppUser);
        } catch (Exception e) {
            model.addAttribute("error", true);
            return "/auth/auth_register";
        }
        return "/auth/auth_login";
    }

}
