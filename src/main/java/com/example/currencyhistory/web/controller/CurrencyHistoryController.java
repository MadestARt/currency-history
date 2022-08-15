package com.example.currencyhistory.web.controller;

import com.example.currencyhistory.service.CurrencyHistoryService;
import com.example.currencyhistory.web.api.CurrenciesHistoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("${app.name}/v1")
@RequiredArgsConstructor
@Slf4j
public class CurrencyHistoryController {
    private final CurrencyHistoryService currencyHistoryService;

    @GetMapping("/currencies/history")
    public ResponseEntity<CurrenciesHistoryResponse> getCurrenciesHistory(
            @RequestParam String pair,
            @RequestParam("from")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime fromDate,
            @RequestParam("to")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime toDate) {
        log.debug("GET HTTP Request to /currencies/history with args {},{},{}",pair,fromDate,toDate);
        return ResponseEntity.ok(currencyHistoryService.getPairHistory(pair,fromDate,toDate));
    }
}
