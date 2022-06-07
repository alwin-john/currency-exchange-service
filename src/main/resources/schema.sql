CREATE TABLE EURO_FOREIGN_EXCHANGE_REFERENCE_RATE(
    id INT NOT NULL,
    currency VARCHAR(10) NOT NULL,
    description VARCHAR(50),
    exchange_rate FLOAT NOT NULL,
    requested_count INT,
    updated_date TIMESTAMP
);