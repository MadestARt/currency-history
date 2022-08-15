package com.example.currencyhistory.dao.repository;

import com.example.currencyhistory.dao.entity.CurrencyHistoryEntity;

import java.sql.Timestamp;
import java.util.List;

public interface CurrencyHistoryRepository {

    List<CurrencyHistoryEntity> getCurrencyHistoryInPeriod(String pair, Timestamp from, Timestamp to);

    void saveCurrencyPriceInfo(CurrencyHistoryEntity entity);
}
