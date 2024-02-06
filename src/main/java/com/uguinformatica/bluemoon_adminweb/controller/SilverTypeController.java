package com.uguinformatica.bluemoon_adminweb.controller;

import com.uguinformatica.bluemoon_adminweb.model.SilverType;
import com.uguinformatica.bluemoon_adminweb.model.User;
import com.uguinformatica.bluemoon_adminweb.service.silvertype.ISilverTypeService;
import com.uguinformatica.bluemoon_adminweb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/app/silvertype")
public class SilverTypeController {

    private final IUserService userService;
    private final ISilverTypeService silverTypeService;

    @Autowired
    public SilverTypeController(IUserService userService, ISilverTypeService silverTypeService) {
        this.userService = userService;
        this.silverTypeService = silverTypeService;
    }

    @GetMapping("/list")
    public String getSilverTypeList(Model model) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);

        model.addAttribute("silvertypes", silverTypeService.getAllSilverTypes(user.getAuthToken()).orElse(null));
        return "/app/silvertype/silvertype_list";
    }

    @GetMapping("/edit/{id}")
    public String getSilverTypeEdit(Model model, @PathVariable long id) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);

        model.addAttribute("silvertype", silverTypeService.getSilverTypeByID(id, user.getAuthToken()).orElse(null));
        return "/app/silvertype/silvertype_edit";
    }

    @PostMapping("/edit/{id}")
    public String postSilverTypeEdit(@PathVariable long id, @ModelAttribute("name") String name, @ModelAttribute("currentPrice") double currentPrice) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.

        SilverType silverType = silverTypeService.getSilverTypeByID(id, user.getAuthToken()).orElse(null);
        if (silverType != null) {
            silverType.setCurrentPrice(currentPrice);
            silverType.setName(name);
            silverTypeService.updateSilverType(silverType, user.getAuthToken());
        }
        return "redirect:/app/silvertype/list";
    }

    @PostMapping("/delete/{id}")
    public String postSilverTypeDelete(@PathVariable long id) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        silverTypeService.deleteSilverType(id, user.getAuthToken());
        return "redirect:/app/silvertype/list";
    }

    @GetMapping("/new")
    public String getSilverTypeNew(Model model) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);
        return "/app/silvertype/silvertype_new";
    }

    @PostMapping("/new")
    public String postSilverTypeNew(@ModelAttribute("name") String name, @ModelAttribute("currentPrice") double currentPrice) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        SilverType silverType = new SilverType();
        silverType.setCurrentPrice(currentPrice);
        silverType.setName(name);
        silverTypeService.createSilverType(silverType, user.getAuthToken());
        return "redirect:/app/silvertype/list";
    }

}
