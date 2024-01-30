package com.uguinformatica.bluemoon_adminweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/silvertype")
public class SilverTypeController {

    @GetMapping("/list")
    public String getTradeList() {
        return "/app/silvertype/silvertype_list";
    }

    @GetMapping("/detail")
    public String getTradeDetail() {
        return "/app/silvertype/silvertype_detail";
    }

}
