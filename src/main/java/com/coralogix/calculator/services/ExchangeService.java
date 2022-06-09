package com.coralogix.calculator.services;

import java.util.List;

import com.coralogix.calculator.model.ExchangeRate;

public interface ExchangeService {

	public ExchangeRate findByCurrency(String originCurrency,String finalCurrency);
	public List<ExchangeRate> findAll(String originCurrency);
}
