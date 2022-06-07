package com.scalable.currencyexchangeservice.service;

import com.scalable.currencyexchangeservice.dto.ConvertCurrencyAmountPayload;
import com.scalable.currencyexchangeservice.dto.ConvertedCurrencyAmountResult;
import com.scalable.currencyexchangeservice.dto.CurrencyPairExchangePayload;
import com.scalable.currencyexchangeservice.dto.CurrencyPairReferenceRateResult;


public interface CurrencyExchangeService {

    CurrencyPairReferenceRateResult getEcbReferenceRateOfCurrencyPair(CurrencyPairExchangePayload
                                                                                     currencyPairInfoForExchange);

    ConvertedCurrencyAmountResult convertCurrencyAmount(ConvertCurrencyAmountPayload convertCurrencyAmountPayload);
}
