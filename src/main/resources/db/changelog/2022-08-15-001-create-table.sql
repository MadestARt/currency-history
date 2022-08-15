--liquibase formatted sql

--changeset asvladimirov:1
CREATE TABLE currency_history(
    price_date TIMESTAMP NOT NULL,
    currency CHAR(6) NOT NULL,
    price REAL NOT NULL,
    PRIMARY KEY (price_date,currency)
)