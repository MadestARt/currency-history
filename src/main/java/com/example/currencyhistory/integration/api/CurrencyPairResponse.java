package com.example.currencyhistory.integration.api;

import com.example.currencyhistory.web.api.CurrencyPairInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPairResponse {
    private String pair;
    private List<CurrencyInfo> currencyPairs;
}
