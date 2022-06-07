package com.scalable.currencyexchangeservice.dto;

import io.swagger.annotations.ApiModelProperty;

public class ConvertCurrencyAmountPayload {

    @ApiModelProperty(notes = " Base currency, eg code : EUR")
    private String baseCurrencyCode;

    @ApiModelProperty(notes = "Target currency, eg code : USD")
    private String targetCurrencyCode;

    @ApiModelProperty(notes = "The requested amount for conversion, eg:15")
    private double amount;

    public ConvertCurrencyAmountPayload(String baseCurrencyCode, String targetCurrencyCode, double amount) {
        this.baseCurrencyCode = baseCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
