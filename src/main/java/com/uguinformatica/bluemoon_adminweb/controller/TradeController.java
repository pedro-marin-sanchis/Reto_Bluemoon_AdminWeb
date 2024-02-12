package com.uguinformatica.bluemoon_adminweb.controller;

import com.uguinformatica.bluemoon_adminweb.model.Trade;
import com.uguinformatica.bluemoon_adminweb.model.Tradeable;
import com.uguinformatica.bluemoon_adminweb.model.User;
import com.uguinformatica.bluemoon_adminweb.service.trade.ITradeService;
import com.uguinformatica.bluemoon_adminweb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping("/app/trade")
public class TradeController {

    private final IUserService userService;
    private final ITradeService tradeService;

    @Autowired
    public TradeController(IUserService userService, ITradeService tradeService) {
        this.userService = userService;
        this.tradeService = tradeService;
    }

    // PLP
    @GetMapping("/list")
    public String getTradeList(Model model) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);

        model.addAttribute("trades", tradeService.getAllTrades(user.getAuthToken()).orElse(null));
        return "app/trade/trade_list";
    }

    // PDP
    @GetMapping("/inspect/{id}")
    public String getTradeInspect(Model model, @PathVariable("id") int id) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);

        model.addAttribute("trade", tradeService.getTradeById(id, user.getAuthToken()).orElse(null));
        return "app/trade/trade_inspect";
    }

    @PostMapping("/inspect/{id}")
    public String postTradeInspect(
            Model model,
            @PathVariable int id,
            @ModelAttribute("approval") Boolean approval) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);

        Trade updatedTrade = tradeService.getTradeById(id, user.getAuthToken()).orElse(null);
        if (updatedTrade != null) {
            updatedTrade.setValidated(approval);
            tradeService.updateTrade(updatedTrade, user.getAuthToken());
        }
        return "redirect:app/trade/list";
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String postTradeDelete(@PathVariable("id") int id) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        tradeService.deleteTrade(id, user.getAuthToken());
        return "redirect:app/trade/list";
    }

}