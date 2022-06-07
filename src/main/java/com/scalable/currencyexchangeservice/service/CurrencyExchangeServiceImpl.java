package com.scalable.currencyexchangeservice.service;

import com.scalable.currencyexchangeservice.dto.ConvertCurrencyAmountPayload;
import com.scalable.currencyexchangeservice.dto.ConvertedCurrencyAmountResult;
import com.scalable.currencyexchangeservice.dto.CurrencyPairExchangePayload;
import com.scalable.currencyexchangeservice.dto.CurrencyPairReferenceRateResult;
import com.scalable.currencyexchangeservice.entity.EuroForeignExchangeReference;
import com.scalable.currencyexchangeservice.enums.CurrencyType;
import com.scalable.currencyexchangeservice.exception.BusinessException;
import com.scalable.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.scalable.currencyexchangeservice.exception.BusinessExceptionReason.INVALID_ARGUMENTS;
import static com.scalable.currencyexchangeservice.exception.BusinessExceptionReason.NOT_FOUND;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private CurrencyExchangeRepository currencyExchangeRepository;


    @Autowired
    CurrencyExchangeServiceImpl(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @Override
    public CurrencyPairReferenceRateResult getEcbReferenceRateOfCurrencyPair(CurrencyPairExchangePayload currencyPairExchangePayload) {

        String baseCurrencyCode = currencyPairExchangePayload.getBaseCurrencyCode();
        String targetCurrencyCode = currencyPairExchangePayload.getTargetCurrencyCode();
        CalculateReferenceRate calculateReferenceRate;

        validateParameters(baseCurrencyCode, targetCurrencyCode);

        if (CurrencyType.EUR.toString().equals(baseCurrencyCode)) {
            calculateReferenceRate = convertFromEuroToAnotherCurrency();
        } else if (CurrencyType.EUR.toString().equals(targetCurrencyCode)) {
            calculateReferenceRate = convertToEuroFromAnotherCurrency();
        } else {
            calculateReferenceRate = convertToGivenCurrencyCode();
        }

        return calculateReferenceRate.calculate(baseCurrencyCode, targetCurrencyCode);
    }

    @Override
    public ConvertedCurrencyAmountResult convertCurrencyAmount(ConvertCurrencyAmountPayload convertCurrencyAmountPayload) {
        // get the reference rate
        CurrencyPairExchangePayload currencyPairExchangePayload =
                new CurrencyPairExchangePayload(convertCurrencyAmountPayload.getBaseCurrencyCode()
                        , convertCurrencyAmountPayload.getTargetCurrencyCode());
        CurrencyPairReferenceRateResult currencyPairReferenceRateResult = getEcbReferenceRateOfCurrencyPair(currencyPairExchangePayload);

        // validate parameter
        ValidateCurrencyAmount(convertCurrencyAmountPayload);
        validateParameters(convertCurrencyAmountPayload.getBaseCurrencyCode(), convertCurrencyAmountPayload.getTargetCurrencyCode());

        // calculate the amount
        double convertedAmount = convertCurrencyAmountPayload.getAmount() * currencyPairReferenceRateResult.getRate();

        return new ConvertedCurrencyAmountResult(currencyPairReferenceRateResult.getDate()
                , convertCurrencyAmountPayload.getBaseCurrencyCode(), convertCurrencyAmountPayload.getTargetCurrencyCode()
                , convertCurrencyAmountPayload.getAmount(), convertedAmount);
    }

    private void ValidateCurrencyAmount(ConvertCurrencyAmountPayload convertCurrencyAmountPayload) {
        if (convertCurrencyAmountPayload.getAmount() < 1) {
            throw new BusinessException(INVALID_ARGUMENTS, "Amount must be greater than 0");
        }
    }

    private void validateParameters(String baseCurrencyCode, String targetCurrencyCode) {
        if (baseCurrencyCode.equals(targetCurrencyCode)) {
            throw new BusinessException(INVALID_ARGUMENTS, "currencyCode is same");
        }
    }

    private CalculateReferenceRate convertFromEuroToAnotherCurrency() {
        CalculateReferenceRate calculateReferenceRate = (baseCurrency, targetCurrency) -> {
            EuroForeignExchangeReference euroForeignExchangeReference = getEuroForeignExchangeReferenceRate(targetCurrency);
            double rate = euroForeignExchangeReference.getExchangeRate();
            return new CurrencyPairReferenceRateResult(baseCurrency, targetCurrency, euroForeignExchangeReference.getUpdatedDate(), rate);
        };

        return calculateReferenceRate;
    }

    private CalculateReferenceRate convertToEuroFromAnotherCurrency() {
        CalculateReferenceRate calculateReferenceRate = (baseCurrency, targetCurrency) -> {
            EuroForeignExchangeReference euroForeignExchangeReference = getEuroForeignExchangeReferenceRate(baseCurrency);
            double rate = 1 / (euroForeignExchangeReference.getExchangeRate());
            return new CurrencyPairReferenceRateResult(baseCurrency, targetCurrency, euroForeignExchangeReference.getUpdatedDate(), roundDecimalToFourPlaces(rate));
        };

        return calculateReferenceRate;
    }

    private CalculateReferenceRate convertToGivenCurrencyCode() {
        CalculateReferenceRate calculateReferenceRate = (baseCurrency, targetCurrency) -> {
            EuroForeignExchangeReference euroForeignExchangeReferenceForFirstCurrency = getEuroForeignExchangeReferenceRate(baseCurrency);
            EuroForeignExchangeReference euroForeignExchangeReferenceForSecondCurrency = getEuroForeignExchangeReferenceRate(targetCurrency);
            double rate = euroForeignExchangeReferenceForSecondCurrency.getExchangeRate() / (euroForeignExchangeReferenceForFirstCurrency.getExchangeRate());
            return new CurrencyPairReferenceRateResult(baseCurrency, targetCurrency, euroForeignExchangeReferenceForFirstCurrency.getUpdatedDate(), roundDecimalToFourPlaces(rate));
        };

        return calculateReferenceRate;
    }

    private EuroForeignExchangeReference getEuroForeignExchangeReferenceRate(String currency) {
        Optional<EuroForeignExchangeReference> euroForeignExchangeReference = Optional.ofNullable(currencyExchangeRepository.findByCurrency(currency));

        if (!euroForeignExchangeReference.isPresent()) {
            throw new BusinessException(NOT_FOUND);
        }

        EuroForeignExchangeReference euroForeignExchangeReferenceRates = updateRequestedCurrencyCount(euroForeignExchangeReference);
        return euroForeignExchangeReferenceRates;
    }

    private EuroForeignExchangeReference updateRequestedCurrencyCount(Optional<EuroForeignExchangeReference> euroForeignExchangeReference) {
        EuroForeignExchangeReference euroForeignExchangeReferenceRates = euroForeignExchangeReference.get();
        int requestedCount = euroForeignExchangeReferenceRates.getRequestedCount() + 1;
        euroForeignExchangeReferenceRates.setRequestedCount(requestedCount);
        currencyExchangeRepository.save(euroForeignExchangeReferenceRates);
        return euroForeignExchangeReferenceRates;
    }

    private double roundDecimalToFourPlaces(double rate) {
        return Math.round(rate * 10000d) / 10000d;
    }

}
