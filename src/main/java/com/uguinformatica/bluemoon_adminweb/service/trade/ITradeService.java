package com.uguinformatica.bluemoon_adminweb.service.trade;

import com.uguinformatica.bluemoon_adminweb.model.Trade;

import java.util.List;
import java.util.Optional;

public interface ITradeService {
    Optional<List<Trade>> getAllTrades();
    Optional<Trade> getTradeById(int id);
    void createTrade(Trade trade);
    void updateTrade(Trade trade);
    void deleteTrade(int id);
}
