package com.example.currencyhistory.service;

import com.example.currencyhistory.web.api.CurrenciesHistoryResponse;

import java.time.LocalDateTime;

public interface CurrencyHistoryService {

    CurrenciesHistoryResponse getPairHistory(String pair, LocalDateTime from, LocalDateTime to);

}
