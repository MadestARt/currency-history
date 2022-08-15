package com.example.currencyhistory.integration.service;

import com.example.currencyhistory.config.CurrencyProperties;
import com.example.currencyhistory.dao.entity.CurrencyHistoryEntity;
import com.example.currencyhistory.dao.repository.CurrencyHistoryRepository;
import com.example.currencyhistory.endpoints.CurrencyGeneratorEndpoints;
import com.example.currencyhistory.integration.api.CurrencyPairResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledClientService {

    private final CurrencyHistoryRepository repository;
    private final RestTemplate restTemplate;
    private final CurrencyProperties currencyProperties;

    @Scheduled(fixedRate = 60000)
    void getCurrencyRates() {
        var currencyPairs = currencyProperties.getAllPairs();
        log.debug("Scheduler Service start request with pairs {}",currencyPairs);
        currencyPairs.forEach(this::getPricesAndSave);
    }

    private void getPricesAndSave(String pair) {
        var pairResponse = restTemplate.getForObject( CurrencyGeneratorEndpoints.CURRENCY_GENERATOR_GET.getUrl() + "/" + pair, CurrencyPairResponse.class);
        pairResponse.getCurrencyPairs().forEach(pairInfo ->  repository.saveCurrencyPriceInfo(new CurrencyHistoryEntity(pairInfo.getTimeStamp(),pairResponse.getPair(),pairInfo.getPrice())));
    }
}
