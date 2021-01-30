package com.pratap.camel.microservice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pratap.camel.microservice.model.beans.CurrencyExchange;
import com.pratap.camel.microservice.processors.MyCurrencyExchangeProcessor;
import com.pratap.camel.microservice.transformers.MyCurrencyExchangeTransformer;

@Component
public class ActiveMqJsonReceiverRouter extends RouteBuilder {

	@Autowired
	private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
	
	@Autowired
	private MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

	@Override
	public void configure() throws Exception {

		from("activemq:my-activemq-json-queue")
		.unmarshal()
		.json(JsonLibrary.Jackson, CurrencyExchange.class)
		.bean(myCurrencyExchangeProcessor, "processMessage")
		.bean(myCurrencyExchangeTransformer, "transformMessage")
		.to("log:received-message-from-active-mq-json");
	}

}
