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

    @GetMapping("/list")
    public String getTradeList(Model model) {
        model.addAttribute("trades", tradeService.getAllTrades().orElse(null));
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);
        return "/app/trade/trade_list";
    }

    @GetMapping("/inspect/{id}")
    public String getTradeInspect(Model model, @PathVariable("id") int id) {
        model.addAttribute("trade", tradeService.getTradeById(id).orElse(null));
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);
        return "/app/trade/trade_inspect";
    }

    @PostMapping("/inspect/{id}")
    public String postTradeInspect(
            Model model,
            @PathVariable int id,
            @ModelAttribute("validated") Boolean validated) {
        Trade updatedTrade = tradeService.getTradeById(id).orElse(null);
        if (updatedTrade != null) {
            updatedTrade.setValidated(validated);
            tradeService.updateTrade(updatedTrade);
        }
        User currentUser = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", currentUser);
        return "redirect:/app/trade/list";
    }

    /*
    @GetMapping("/new")
    public String getTradeNew(Model model) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);
        return "/app/trade/trade_new";
    }
    */

    @PostMapping("/delete/{id}")
    public String postTradeDelete(@PathVariable("id") int id) {
        tradeService.deleteTrade(id);
        return "redirect:/app/trade/list";
    }

    /*
    @PostMapping("/new")
    public String postTradeNew(
            Model model,
            @ModelAttribute("date") Date date,
            @ModelAttribute("validated") Boolean validated,
            @ModelAttribute("user") User user,
            @ModelAttribute("tradeables") Set<Tradeable> tradeables) {
        Trade trade = new Trade();
        trade.setDate(date);
        trade.setValidated(validated);
        trade.setUser(user);
        trade.setTradeables(tradeables);
        tradeService.createTrade(trade);
        User currentUser = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", currentUser);
        return "redirect:/app/trade/list";
    }
    */

}