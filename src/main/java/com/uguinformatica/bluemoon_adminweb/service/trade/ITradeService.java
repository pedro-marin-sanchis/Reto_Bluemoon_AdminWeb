package com.uguinformatica.bluemoon_adminweb.service.trade;

import com.uguinformatica.bluemoon_adminweb.model.Trade;

import java.util.List;
import java.util.Optional;

public interface ITradeService {
    Optional<List<Trade>> getAllTrades(String token);
    Optional<Trade> getTradeById(int id, String token);
    void updateTrade(Trade trade, String token);
    void deleteTrade(int id, String token);
}
