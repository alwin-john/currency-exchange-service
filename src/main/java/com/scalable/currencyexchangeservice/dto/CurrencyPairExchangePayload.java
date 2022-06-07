package com.scalable.currencyexchangeservice.dto;

import io.swagger.annotations.ApiModelProperty;

public class CurrencyPairExchangePayload {

    @ApiModelProperty(notes = "Specify the base currency code. for example if you want to get the exchange rate of USD against Euro, base currency will be EUR")
    private String baseCurrencyCode;

    @ApiModelProperty(notes = "Target currency, eg code : USD")
    private String targetCurrencyCode;

    public CurrencyPairExchangePayload(String baseCurrencyCode, String targetCurrencyCode) {
        this.baseCurrencyCode = baseCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
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
}
