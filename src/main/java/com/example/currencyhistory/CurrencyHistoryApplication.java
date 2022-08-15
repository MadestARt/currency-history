package com.example.currencyhistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CurrencyHistoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyHistoryApplication.class, args);
    }

}
