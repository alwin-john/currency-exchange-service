package com.scalable.currencyexchangeservice.dto;

import java.time.LocalDateTime;

public class CurrencyPairReferenceRateResult {

    private String baseCurrencyCode;

    private String targetCurrencyCode;

    private LocalDateTime date;

    private double rate;

    public CurrencyPairReferenceRateResult(){

    }

    public CurrencyPairReferenceRateResult(String baseCurrencyCode, String targetCurrencyCode, LocalDateTime date, double rate) {
        this.baseCurrencyCode = baseCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        this.date = date;
        this.rate = rate;
    }

    public String getBaseCurrencyCode() {
        return baseCurrencyCode;
    }

    public void setBaseCurrencyCode(String baseCurrencyCode) {
        this.baseCurrencyCode = baseCurrencyCode;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    public void setTargetCurrencyCode(String targetCurrencyCode) {
        this.targetCurrencyCode = targetCurrencyCode;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
