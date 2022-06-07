package com.scalable.currencyexchangeservice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="EURO_FOREIGN_EXCHANGE_REFERENCE_RATE")
public class EuroForeignExchangeReference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "currency")
    private String currency;

    @Column(name = "description")
    private String description;

    @Column(name = "exchange_rate")
    private double exchangeRate;

    @Column(name = "requested_count")
    private int requestedCount;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public EuroForeignExchangeReference(){

    }

    public EuroForeignExchangeReference(long id, String currency, String description, double exchangeRate, int requestedCount
            , LocalDateTime updatedDate) {
        this.id = id;
        this.currency = currency;
        this.description = description;
        this.exchangeRate = exchangeRate;
        this.requestedCount = requestedCount;
        this.updatedDate = updatedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getRequestedCount() {
        return requestedCount;
    }

    public void setRequestedCount(int requestedCount) {
        this.requestedCount = requestedCount;
    }
}
