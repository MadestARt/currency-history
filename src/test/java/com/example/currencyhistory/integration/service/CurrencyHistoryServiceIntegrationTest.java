package com.example.currencyhistory.integration.service;

import com.example.currencyhistory.integration.IntegrationTestBase;
import com.example.currencyhistory.service.impl.CurrencyHistoryServiceImpl;
import com.example.currencyhistory.web.api.CurrenciesHistoryResponse;
import com.example.currencyhistory.web.api.CurrencyPairInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

class CurrencyHistoryServiceIntegrationTest extends IntegrationTestBase {
    @Autowired
    private CurrencyHistoryServiceImpl currencyHistoryService;

    private static final String TEST_CURRENCY_PAIR = "GBPEUR";

    @Test
    @DisplayName("Проверяем,что сервис достаёт от репозитория правильные данные и возвращает корректный респорнс")
    void testGetPairHistoryAndRepoWorksCorrect() {
        var expectedResponse = new CurrenciesHistoryResponse(TEST_CURRENCY_PAIR, List.of(new CurrencyPairInfo(LocalDateTime.of(2022,8,15,15,44,58),1.764),
                new CurrencyPairInfo(LocalDateTime.of(2022,8,15,15,46,58),1.763)));

        var actualResponse = currencyHistoryService.getPairHistory(TEST_CURRENCY_PAIR, LocalDateTime.of(2022, 8, 15, 13, 15), LocalDateTime.of(2022, 8, 15, 23, 15));

        Assertions.assertThat(expectedResponse).isEqualTo(actualResponse);
    }
}
