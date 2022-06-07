package com.scalable.currencyexchangeservice.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SupportedCurrencyData {

    private String baseCurrency;

    private LocalDateTime date;

    private List<Currency> currencyList;


    public SupportedCurrencyData(String baseCurrency, LocalDateTime date, List<Currency> currencyList) {
        this.baseCurrency = baseCurrency;
        this.date = date;
        this.currencyList = currencyList;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}
