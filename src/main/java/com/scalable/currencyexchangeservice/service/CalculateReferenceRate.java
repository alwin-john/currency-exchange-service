package com.scalable.currencyexchangeservice.service;

import com.scalable.currencyexchangeservice.dto.CurrencyPairReferenceRateResult;

public interface CalculateReferenceRate {

    public CurrencyPairReferenceRateResult calculate(String baseCurrencyCode, String targetCurrencyCode);

}
