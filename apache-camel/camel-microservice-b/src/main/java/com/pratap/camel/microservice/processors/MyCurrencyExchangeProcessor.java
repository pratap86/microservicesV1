package com.pratap.camel.microservice.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pratap.camel.microservice.model.beans.CurrencyExchange;

@Component
public class MyCurrencyExchangeProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);

	public void processMessage(CurrencyExchange currencyExchange) {
		LOGGER.info("Do some processing with currencyExchange.getConversionMultiple() value which value is, {}",
				currencyExchange.getConversionMultiple());
	}
}
