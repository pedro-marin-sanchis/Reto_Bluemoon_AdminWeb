package com.uguinformatica.bluemoon_adminweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/trade")
public class TradeController {

    @GetMapping("/list")
    public String getTradeList() {
        return "/app/trade/trade_list";
    }

    @GetMapping("/detail")
    public String getTradeDetail() {
        return "/app/trade/trade_detail";
    }

}
