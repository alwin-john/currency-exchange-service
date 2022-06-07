package com.scalable.currencyexchangeservice.controller;

import com.scalable.currencyexchangeservice.dto.ConvertCurrencyAmountPayload;
import com.scalable.currencyexchangeservice.dto.ConvertedCurrencyAmountResult;
import com.scalable.currencyexchangeservice.dto.CurrencyPairExchangePayload;
import com.scalable.currencyexchangeservice.dto.CurrencyPairReferenceRateResult;
import com.scalable.currencyexchangeservice.service.CurrencyExchangeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency-exchange/api/v1")
public class CurrencyExchangeController {

    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    CurrencyExchangeController(CurrencyExchangeService currencyExchangeService){
        this.currencyExchangeService = currencyExchangeService;
    }

    @ApiOperation(value = "Get the ECB reference for a given currency pair. Conversion to and from Euro and other pair are handled in this end point")
    @PostMapping("/ecb-reference-of-currency-pair")
    public ResponseEntity<CurrencyPairReferenceRateResult> getEcbReferenceRateOfCurrencyPair(@RequestBody CurrencyPairExchangePayload currencyPairInfoForExchange){

        return new ResponseEntity<>(currencyExchangeService
                .getEcbReferenceRateOfCurrencyPair(currencyPairInfoForExchange), HttpStatus.OK);
    }

    @ApiOperation(value = "Convert a given currency amount to another currency")
    @PostMapping("/convert-currency-amount")
    public ResponseEntity<ConvertedCurrencyAmountResult> getConvertedCurrencyAmount(@RequestBody ConvertCurrencyAmountPayload currencyAmountPayload){

        return new ResponseEntity<>(currencyExchangeService
                .convertCurrencyAmount(currencyAmountPayload), HttpStatus.OK);
    }
}
