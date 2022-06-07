package com.scalable.currencyexchangeservice.service;

import com.scalable.currencyexchangeservice.dto.ConvertCurrencyAmountPayload;
import com.scalable.currencyexchangeservice.dto.ConvertedCurrencyAmountResult;
import com.scalable.currencyexchangeservice.dto.CurrencyPairExchangePayload;
import com.scalable.currencyexchangeservice.dto.CurrencyPairReferenceRateResult;
import com.scalable.currencyexchangeservice.entity.EuroForeignExchangeReference;
import com.scalable.currencyexchangeservice.repository.CurrencyExchangeRepository;
import com.scalable.currencyexchangeservice.util.CommonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CurrencyExchangeServiceImplTests {

    @Mock
    private CurrencyExchangeRepository currencyExchangeRepository;

    private CurrencyExchangeService currencyExchangeService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        currencyExchangeService = new CurrencyExchangeServiceImpl(currencyExchangeRepository);
    }

    @Test
    void getEcbReferenceRateOfCurrencyPairTest() {
        CurrencyPairExchangePayload currencyPairInfoForExchange  = new CurrencyPairExchangePayload("EUR","USD");
        when(currencyExchangeRepository.findByCurrency("USD")).thenReturn(getEuroForeignExchangeReference());
        when(currencyExchangeRepository.save(any())).thenReturn(getEuroForeignExchangeReference());
        CurrencyPairReferenceRateResult currencyPairReferenceRate = currencyExchangeService.getEcbReferenceRateOfCurrencyPair(currencyPairInfoForExchange);
        assertEquals(1.1326, currencyPairReferenceRate.getRate());
    }

    @Test
    void getEcbReferenceRateOfCurrencyPairWhenConvertFromOtherCurrencyToEuroTest() {
        CurrencyPairExchangePayload currencyPairInfoForExchange  = new CurrencyPairExchangePayload("USD","EUR");
        when(currencyExchangeRepository.findByCurrency("USD")).thenReturn(getEuroForeignExchangeReference());
        CurrencyPairReferenceRateResult currencyPairReferenceRate = currencyExchangeService.getEcbReferenceRateOfCurrencyPair(currencyPairInfoForExchange);
        assertEquals(0.8829,currencyPairReferenceRate.getRate());
    }

    @Test
    void getEcbReferenceRateOfCurrencyPairTest_convertToGivenCurrencyCode() {
        CurrencyPairExchangePayload currencyPairInfoForExchange  = new CurrencyPairExchangePayload("USD","INR");
        when(currencyExchangeRepository.findByCurrency(anyString())).thenReturn(getEuroForeignExchangeReference())
                .thenReturn(getEuroForeignExchangeReferenceForIndianRupee());
        when(currencyExchangeRepository.save(any())).thenReturn(getEuroForeignExchangeReference());
        CurrencyPairReferenceRateResult currencyPairReferenceRate = currencyExchangeService.getEcbReferenceRateOfCurrencyPair(currencyPairInfoForExchange);
        assertEquals(74.5143, currencyPairReferenceRate.getRate());
    }

    @Test
    void convertCurrencyAmountTest() {
        ConvertCurrencyAmountPayload convertCurrencyAmountPayload  = new ConvertCurrencyAmountPayload("USD","INR",10);
        when(currencyExchangeRepository.findByCurrency(anyString())).thenReturn(getEuroForeignExchangeReference())
                .thenReturn(getEuroForeignExchangeReferenceForIndianRupee());
        when(currencyExchangeRepository.save(any())).thenReturn(getEuroForeignExchangeReference());
        ConvertedCurrencyAmountResult convertedCurrencyAmount = currencyExchangeService.convertCurrencyAmount(convertCurrencyAmountPayload);
        assertEquals(745.143, convertedCurrencyAmount.getConvertedAmount());
    }

    private EuroForeignExchangeReference getEuroForeignExchangeReference(){
        return new EuroForeignExchangeReference(1, "USD"
                ,"US DOLLARS",1.1326, 1, CommonUtil.getCurrentTimeStamp());

    }

    private EuroForeignExchangeReference getEuroForeignExchangeReferenceForIndianRupee(){
        return new EuroForeignExchangeReference(1, "INR"
                ,"Indian rupee",84.3949, 1, CommonUtil.getCurrentTimeStamp());

    }
}