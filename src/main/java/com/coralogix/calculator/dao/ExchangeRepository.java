package com.coralogix.calculator.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.coralogix.calculator.model.ExchangeRate;

public interface ExchangeRepository extends CrudRepository<ExchangeRate, Long>{
	
	public ExchangeRate findByOriginCurrencyAndFinalCurrency(String originCurrency,String finalCurrency);
	public List<ExchangeRate> findByOriginCurrency(String originCurrency);

}
