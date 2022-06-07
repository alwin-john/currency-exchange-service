package com.scalable.currencyexchangeservice.service;

import com.scalable.currencyexchangeservice.dto.SupportedCurrencyData;
import com.scalable.currencyexchangeservice.entity.EuroForeignExchangeReference;
import com.scalable.currencyexchangeservice.repository.CurrencyRepository;
import com.scalable.currencyexchangeservice.util.CommonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CurrencyServiceImplTests {

    @Mock
    private CurrencyRepository currencyRepository;

    private CurrencyService currencyService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        currencyService = new CurrencyServiceImpl(currencyRepository);
    }

    @Test
    void getSupportedCurrencyDataTest() {
       when(currencyRepository.findAll()).thenReturn(getEuroForeignExchangeReferenceRates());
       SupportedCurrencyData supportedCurrencyData = currencyService.getSupportedCurrencyData();
       assertEquals(1,supportedCurrencyData.getCurrencyList().size());
       assertEquals("EUR",supportedCurrencyData.getBaseCurrency());
    }

    private  List<EuroForeignExchangeReference> getEuroForeignExchangeReferenceRates(){
        List<EuroForeignExchangeReference> euroForeignExchangeReferences = new ArrayList<>();
        euroForeignExchangeReferences.add(new EuroForeignExchangeReference(1, "USD"
                ,"US DOLLARS",1.1326, 1, CommonUtil.getCurrentTimeStamp()));
        return euroForeignExchangeReferences;
    }
}