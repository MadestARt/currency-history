package com.example.currencyhistory.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrenciesHistoryResponse {
    private String currency;
    private List<CurrencyPairInfo> pairPricesHistory;
}
