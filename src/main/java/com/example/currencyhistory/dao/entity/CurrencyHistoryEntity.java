package com.example.currencyhistory.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyHistoryEntity {
    private LocalDateTime priceDate;
    private String currency;
    private double price;
}
