package com.uguinformatica.bluemoon_adminweb.controller;

import com.uguinformatica.bluemoon_adminweb.model.AppUser;
import com.uguinformatica.bluemoon_adminweb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/profile")
public class ProfileController {

    private final IUserService userService;

    @Autowired
    public ProfileController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/overview")
    public String getProfileOverview(Model model) {
        AppUser user = userService.getCurrentUser().get(); // Get current user.

        if (user != null) {
            model.addAttribute("user", user);
            return "/app/profile/profile_overview";
        } else {
            return "redirect:/login";
        }
    }
}
