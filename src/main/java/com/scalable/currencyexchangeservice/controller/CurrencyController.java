package com.scalable.currencyexchangeservice.controller;

import com.scalable.currencyexchangeservice.dto.SupportedCurrencyData;
import com.scalable.currencyexchangeservice.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency/api/v1")
public class CurrencyController {

    private CurrencyService currencyService;

    @Autowired
    CurrencyController(CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    @GetMapping("/supported-currency-list")
    public ResponseEntity<SupportedCurrencyData> getEcbReferenceRateOfCurrencyPair(){

        return new ResponseEntity<>(currencyService.getSupportedCurrencyData(), HttpStatus.OK);
    }

}
