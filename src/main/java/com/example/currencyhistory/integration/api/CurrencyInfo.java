package com.example.currencyhistory.integration.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyInfo {
    private LocalDateTime timeStamp;
    private double price;
}
