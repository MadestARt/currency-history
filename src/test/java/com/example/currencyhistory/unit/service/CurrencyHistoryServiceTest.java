package com.example.currencyhistory.unit.service;

import com.example.currencyhistory.dao.entity.CurrencyHistoryEntity;
import com.example.currencyhistory.dao.repository.impl.CurrencyHistoryRepositoryImpl;
import com.example.currencyhistory.exception.PairInfoNotFoundException;
import com.example.currencyhistory.service.impl.CurrencyHistoryServiceImpl;
import com.example.currencyhistory.web.api.CurrenciesHistoryResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyHistoryServiceTest {

    @Mock
    private CurrencyHistoryRepositoryImpl currencyHistoryRepository;

    @InjectMocks
    private CurrencyHistoryServiceImpl currencyHistoryService;

    private static final String TEST_PAIR = "GBPUSD";
    private static final List<CurrencyHistoryEntity> ENTITY_LIST_TO_RETURN = List.of(new CurrencyHistoryEntity(LocalDateTime.now(),TEST_PAIR,1.945));

    @Test
    @DisplayName("Проверяем,что getPairHistory обращается к репозиторию и возвращает корретный респонс,не выбрасывая исключения")
    void testGetPairHistoryWorksCorrectIfSomeData(){
        doReturn(ENTITY_LIST_TO_RETURN).when(currencyHistoryRepository).getCurrencyHistoryInPeriod(anyString(),any(),any());
        int expectedListSize = 1;

        var currenciesHistoryResponse = assertDoesNotThrow(() -> currencyHistoryService.getPairHistory(TEST_PAIR, LocalDateTime.now(), LocalDateTime.now()));
        assertEquals(expectedListSize,currenciesHistoryResponse.getPairPricesHistory().size());

    }

    @Test
    @DisplayName("Проверяем,что getPairHistory обращается к репозиторию и пробрасывает исключение,если репозиторий вернул пустой список")
    void testGetPairHistoryWorksCorrectIfNoData() {
        doReturn(List.of()).when(currencyHistoryRepository).getCurrencyHistoryInPeriod(anyString(),any(),any());

        assertThrows(PairInfoNotFoundException.class, () -> currencyHistoryService.getPairHistory(TEST_PAIR, LocalDateTime.now(), LocalDateTime.now()));
    }

}
