package com.scalable.currencyexchangeservice.repository;

import com.scalable.currencyexchangeservice.entity.EuroForeignExchangeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<EuroForeignExchangeReference, Integer> {

    @Override
    List<EuroForeignExchangeReference> findAll();
}
