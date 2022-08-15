package com.example.currencyhistory.dao.repository.impl;

import com.example.currencyhistory.dao.entity.CurrencyHistoryEntity;
import com.example.currencyhistory.dao.repository.CurrencyHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CurrencyHistoryRepositoryImpl implements CurrencyHistoryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String SELECT_ALL_CURRENCY_PRICES_IN_PERIOD = "SELECT * " +
            "FROM currency_history WHERE currency=:currency " +
            "AND price_date BETWEEN :fromDate and :toDate";
    private static final String INSERT_NEW_CURRENCY_ENTITY = "INSERT INTO currency_history(price_date,currency,price) " +
            "VALUES (:price_date,:currency,:price)";

    @Override
    public List<CurrencyHistoryEntity> getCurrencyHistoryInPeriod(String pair, Timestamp from, Timestamp to) {
        try {
            log.debug("Entered in method getCurrencyHistoryInPeriod of Repository with args {},{},{}",pair,from,to );
            return jdbcTemplate.query(SELECT_ALL_CURRENCY_PRICES_IN_PERIOD,
                    Map.of("currency", pair, "fromDate", from, "toDate", to)
                    , (rs, rn) -> new CurrencyHistoryEntity(rs.getTimestamp("price_date").toLocalDateTime(), rs.getString("currency"), rs.getDouble("price")));
        } catch (EmptyResultDataAccessException exception) {
            log.warn("No actual rows found by repository with args {},{},{}",pair,from,to);
            return List.of();
        }
    }

    @Override
    public void saveCurrencyPriceInfo(CurrencyHistoryEntity currencyHistoryEntity) {

        try {
            jdbcTemplate.update(INSERT_NEW_CURRENCY_ENTITY, Map.of("price_date", currencyHistoryEntity.getPriceDate(), "currency", currencyHistoryEntity.getCurrency(), "price", currencyHistoryEntity.getPrice()));
        } catch (Exception exception) {
            log.warn("Data already exists , inserting into database canceled");
        }

    }
}
