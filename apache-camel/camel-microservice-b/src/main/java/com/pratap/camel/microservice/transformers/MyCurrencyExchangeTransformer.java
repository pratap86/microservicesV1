package com.pratap.camel.microservice.transformers;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pratap.camel.microservice.model.beans.CurrencyExchange;
import com.pratap.camel.microservice.model.responses.CurrencyExchangeResponse;

@Component
public class MyCurrencyExchangeTransformer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyCurrencyExchangeTransformer.class);
	
	private ModelMapper modelMapper;
	
	public CurrencyExchangeResponse transformMessage(CurrencyExchange currencyExchange) {
		
		modelMapper = new ModelMapper();
		LOGGER.info("Transforming the currencyExchange.getConversionMultiple() which current value is {}", currencyExchange.getConversionMultiple());
		currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));
		CurrencyExchangeResponse currencyExchangeResponse = modelMapper.map(currencyExchange, CurrencyExchangeResponse.class);
		return currencyExchangeResponse;
	}
}
