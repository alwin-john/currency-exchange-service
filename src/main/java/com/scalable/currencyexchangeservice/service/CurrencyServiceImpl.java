package com.scalable.currencyexchangeservice.service;

import com.scalable.currencyexchangeservice.dto.Currency;
import com.scalable.currencyexchangeservice.dto.SupportedCurrencyData;
import com.scalable.currencyexchangeservice.entity.EuroForeignExchangeReference;
import com.scalable.currencyexchangeservice.enums.CurrencyType;
import com.scalable.currencyexchangeservice.repository.CurrencyRepository;
import com.scalable.currencyexchangeservice.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public SupportedCurrencyData getSupportedCurrencyData() {

        List<EuroForeignExchangeReference> euroForeignExchangeReferences = currencyRepository.findAll();
        List<Currency> currencies = euroForeignExchangeReferences.stream().map(currencyInfo ->
                new Currency(currencyInfo.getCurrency(),
                        currencyInfo.getExchangeRate(), currencyInfo.getRequestedCount())).collect(Collectors.toList());
        return new SupportedCurrencyData(CurrencyType.EUR.toString(), CommonUtil.getCurrentTimeStamp(), currencies);
    }

}
