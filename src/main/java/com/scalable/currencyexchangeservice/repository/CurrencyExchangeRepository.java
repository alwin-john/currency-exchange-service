package com.scalable.currencyexchangeservice.repository;

import com.scalable.currencyexchangeservice.entity.EuroForeignExchangeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<EuroForeignExchangeReference, Integer> {

    EuroForeignExchangeReference findByCurrency(String currencyCode);

    EuroForeignExchangeReference save(EuroForeignExchangeReference euroForeignExchangeReference);

}
