package com.coralogix.calculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.coralogix.calculator.model.ExchangeRate;
import com.coralogix.calculator.services.ExchangeService;
import com.coralogix.calculator.model.ExchangeRate;

@RestController
@RequestMapping("exchange")
public class Exchange {
	
	 @Autowired
	 @Qualifier("restTemplate")
	 private ExchangeService exchangeService;
	
	@GetMapping()
    public ExchangeRate getExchangeRate(@RequestParam String originCurrency, @RequestParam String finalCurrency){
    	return exchangeService.findByCurrency(originCurrency, finalCurrency);
    }
	

	@GetMapping("/list")
    public List<ExchangeRate> getAllExchangeRate(@RequestParam String originCurrency){
    	return exchangeService.findAll(originCurrency);
    }
	
	

}
