package com.scalable.currencyexchangeservice.dto;

public class Currency {

    private String currencyCode;

    private double exchangeRate;

    private int requestCount;

    public Currency(String currencyCode, double exchangeRate, int requestCount) {
        this.currencyCode = currencyCode;
        this.exchangeRate = exchangeRate;
        this.requestCount = requestCount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }
}
