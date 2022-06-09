package com.coralogix.calculator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="exchange_rates")
public class ExchangeRate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="origin_currency")
	private String originCurrency;
	
	@Column(name="final_currency")
	private String  finalCurrency;
	
	private String  date;
	
	private String  value;
	
	
	
	public ExchangeRate() {
		
	}
	

	public ExchangeRate(String originCurrency, String finalCurrency, String date, String value) {
		super();
		this.originCurrency = originCurrency;
		this.finalCurrency = finalCurrency;
		this.date = date;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	

	public String getOriginCurrency() {
		return originCurrency;
	}

	public void setOriginCurrency(String originCurrency) {
		this.originCurrency = originCurrency;
	}

	public String getFinalCurrency() {
		return finalCurrency;
	}

	public void setFinalCurrency(String finalCurrency) {
		this.finalCurrency = finalCurrency;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}

