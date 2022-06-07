package com.scalable.currencyexchangeservice.dto;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class ConvertedCurrencyAmountResult {

    private LocalDateTime date;

    @ApiModelProperty(notes = " Base currency, eg code : EUR")
    private String baseCurrencyCode;

    @ApiModelProperty(notes = "Target currency, eg code : USD")
    private String targetCurrencyCode;

    @ApiModelProperty(notes = "The requested amount for conversion, eg:15")
    private double amount;

    @ApiModelProperty(notes = "Converted Amount")
    private double convertedAmount;

    public ConvertedCurrencyAmountResult(LocalDateTime date, String baseCurrencyCode, String targetCurrencyCode, double amount, double convertedAmount) {
        this.date = date;
        this.baseCurrencyCode = baseCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}
