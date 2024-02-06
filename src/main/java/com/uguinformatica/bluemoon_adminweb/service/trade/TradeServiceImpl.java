package com.uguinformatica.bluemoon_adminweb.service.trade;

import com.uguinformatica.bluemoon_adminweb.model.Trade;
import com.uguinformatica.bluemoon_adminweb.service.APIConstants;
import com.uguinformatica.bluemoon_adminweb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
    public Optional<List<Trade>> getAllTrades(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<Trade>> responseType = new ParameterizedTypeReference<List<Trade>>() {};
        Optional<List<Trade>> trades = Optional.ofNullable(
                restTemplate.exchange(
                        APIConstants.API_URL + "/trades",
                        HttpMethod.GET,
                        entity,
                        responseType).getBody());
        if (trades.isPresent()) {
            List<Trade> filteredAndSortedSilverTypes = trades.get().stream()
                    .sorted(Comparator.comparingLong(Trade::getId))
                    .collect(Collectors.toList());
            return Optional.of(filteredAndSortedSilverTypes);
        }
        return trades;
    }

    @Override
    public Optional<Trade> getTradeById(int id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        return Optional.ofNullable(restTemplate.exchange(APIConstants.API_URL + "/trades/" + id, HttpMethod.GET, entity, Trade.class).getBody());
    }

    @Override
    public void updateTrade(Trade trade, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Trade> requestEntity = new HttpEntity<>(trade, headers);
        restTemplate.put(APIConstants.API_URL + "/trades/" + trade.getId(), requestEntity);
    }

    @Override
    public void deleteTrade(int id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        restTemplate.exchange(APIConstants.API_URL + "/trades/" + id, HttpMethod.DELETE, entity, Object.class);
    }
}