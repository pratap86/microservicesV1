package com.pratap.microservices.currencyconversion.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pratap.microservices.currencyconversion.entities.CurrencyConversion;
import com.pratap.microservices.currencyconversion.restclients.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {

private static final String URL = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;

	@GetMapping("resttemplate/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionRestTemplate(@PathVariable("from") String from, 
			@PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity) {
		
		LOGGER.info("calculateCurrencyConversionRestTemplate called from {} and to {}", from, to);
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(URL, CurrencyConversion.class, uriVariables);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		
		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+" "+"RestTemplate");
	}
	
	@GetMapping("feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
				
		LOGGER.info("calculateCurrencyConversionFeign called from {} and to {}", from, to);
		CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);
		
		return new CurrencyConversion(currencyConversion.getId(), 
				from, to, quantity, 
				currencyConversion.getConversionMultiple(), 
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+" "+"feign");
		
	}
}
