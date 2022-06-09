package com.coralogix.calculator.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coralogix.calculator.dao.ExchangeRepository;
import com.coralogix.calculator.model.ExchangeRate;
import com.coralogix.calculator.model.exchangeExternal;
import com.coralogix.calculator.services.ExchangeService;



@Service("restTemplate")
public class ExchangeServiceImpl implements ExchangeService{
	
	private List<ExchangeRate> lsExchanges;
	private RestTemplate clienteRest = new RestTemplate();
	
	@Autowired
	private ExchangeRepository exchangeRepository;
	
	

	@Override
	public ExchangeRate findByCurrency(String originCurrency,String finalCurrency) {
		// TODO Auto-generated method stub
		
		ExchangeRate Exchanges =null;
		
		//Consulta a la bade de datos
		Exchanges = exchangeRepository.findByOriginCurrencyAndFinalCurrency(originCurrency, finalCurrency);
		
		
		//Consulta a API externa
		if(Exchanges==null) {
			Map<String,String> parameters = new HashMap();
			
			parameters.put("base",originCurrency);
			parameters.put("symbols",finalCurrency);
			
			HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    headers.add("apiKey", "OErqAKvh8bLyI0rEJtKp8EyFwNcAHgYk");
		    HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			
		    exchangeExternal  exchangesExternal = clienteRest.exchange("https://api.apilayer.com/fixer/latest",HttpMethod.GET,entity,exchangeExternal.class,parameters).getBody();
		      
		    Exchanges = new ExchangeRate(originCurrency, finalCurrency, exchangesExternal.getDate(), exchangesExternal.getRates().get(finalCurrency).toString());
		    
			
		}
		
		
		return Exchanges;
	}



	@Override
	public List<ExchangeRate> findAll(String originCurrency) {
		// TODO Auto-generated method stub
		
		//Consulta a la base de datos
		lsExchanges = exchangeRepository.findByOriginCurrency(originCurrency);
		
		
		//Consulta a API externa
		if(lsExchanges==null || lsExchanges.size()==0) {
			Map<String,String> parameters = new HashMap();
			parameters.put("base",originCurrency);
			
			
			HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    headers.add("apiKey", "OErqAKvh8bLyI0rEJtKp8EyFwNcAHgYk");
		    HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			
		    exchangeExternal  exchangesExternal= exchangesExternal = clienteRest.exchange("https://api.apilayer.com/fixer/latest",HttpMethod.GET,entity,exchangeExternal.class,parameters).getBody();
		    
		    for(Entry<String,Double> rates:exchangesExternal.getRates().entrySet()) {
		    	lsExchanges.add(new ExchangeRate(originCurrency,rates.getKey(),exchangesExternal.getDate(),rates.getValue().toString()));
		    }

		}
		
		 
		
	    
		return lsExchanges;
		
	}
	
	
	


}
