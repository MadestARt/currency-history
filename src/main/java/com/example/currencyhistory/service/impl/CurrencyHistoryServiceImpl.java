package com.example.currencyhistory.service.impl;

import com.example.currencyhistory.dao.entity.CurrencyHistoryEntity;
import com.example.currencyhistory.dao.repository.CurrencyHistoryRepository;
import com.example.currencyhistory.exception.PairInfoNotFoundException;
import com.example.currencyhistory.service.CurrencyHistoryService;
import com.example.currencyhistory.web.api.CurrenciesHistoryResponse;
import com.example.currencyhistory.web.api.CurrencyPairInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyHistoryServiceImpl implements CurrencyHistoryService {
    private final CurrencyHistoryRepository historyRepository;


    @Override
    public CurrenciesHistoryResponse getPairHistory(String pair, LocalDateTime from, LocalDateTime to) {
        log.debug("Entered in method getPairHistory of CurrencyHistoryServiceImpl with args {},{},{}",pair,from,to);
        var currencyHistoryList = historyRepository.getCurrencyHistoryInPeriod(pair, Timestamp.valueOf(from), Timestamp.valueOf(to));
        if (!currencyHistoryList.isEmpty()) {
            return new CurrenciesHistoryResponse(pair,mapToCurrencyPairInfoList(currencyHistoryList));
        } else {

            throw new PairInfoNotFoundException();
        }
    }

    private List<CurrencyPairInfo> mapToCurrencyPairInfoList(List<CurrencyHistoryEntity> currencyHistoryEntities) {
        return currencyHistoryEntities.stream()
                .map(entity -> new CurrencyPairInfo(entity.getPriceDate(),entity.getPrice()))
                .collect(toList());
    }
}
