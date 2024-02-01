package com.uguinformatica.bluemoon_adminweb.service.trade;

import com.uguinformatica.bluemoon_adminweb.model.Trade;
import com.uguinformatica.bluemoon_adminweb.service.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TradeServiceImpl implements ITradeService {

    private final RestTemplate restTemplate;

    @Autowired
    public TradeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<List<Trade>> getAllTrades() {
        ParameterizedTypeReference<List<Trade>> responseType = new ParameterizedTypeReference<List<Trade>>() {};
        Optional<List<Trade>> silverTypes = Optional.ofNullable(restTemplate.exchange(APIConstants.API_URL + "/trades", HttpMethod.GET, null, responseType).getBody());
        if (silverTypes.isPresent()) {
            List<Trade> filteredAndSortedSilverTypes = silverTypes.get().stream()
                    .sorted(Comparator.comparingLong(Trade::getId))
                    .collect(Collectors.toList());
            return Optional.of(filteredAndSortedSilverTypes);
        }
        return silverTypes;
    }

    @Override
    public Optional<Trade> getTradeById(int id) {
        return Optional.ofNullable(restTemplate.getForEntity(APIConstants.API_URL + "/trades/" + id, Trade.class).getBody());
    }

    @Override
    public void createTrade(Trade trade) {
        HttpEntity<Trade> requestEntity = new HttpEntity<>(trade);
        restTemplate.exchange(
                APIConstants.API_URL + "/trades/",
                HttpMethod.POST,
                requestEntity,
                Trade.class
        );
    }

    @Override
    public void updateTrade(Trade trade) {
        HttpEntity<Trade> requestEntity = new HttpEntity<>(trade);
        restTemplate.put(APIConstants.API_URL + "/trades/" + trade.getId(), requestEntity);
    }

    @Override
    public void deleteTrade(int id) {
        restTemplate.delete(APIConstants.API_URL + "/trades/" + id);
    }
}