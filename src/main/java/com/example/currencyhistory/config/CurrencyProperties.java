package com.example.currencyhistory.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "currency")
public class CurrencyProperties {
    private String pairs;

    public List<String> getAllPairs() {
        return List.of(pairs.split("_"));
    }
}
