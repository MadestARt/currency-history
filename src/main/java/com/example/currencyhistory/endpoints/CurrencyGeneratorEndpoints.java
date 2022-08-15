package com.example.currencyhistory.endpoints;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurrencyGeneratorEndpoints {
    CURRENCY_GENERATOR_GET("http://localhost:8081/currency-generator/v1/currency");

    private String url;
}
