package com.pratap.camel.microservice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.pratap.camel.microservice.model.beans.CurrencyExchange;

@Component
public class ActiveMqXmlReceiverRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("activemq:my-activemq-xml-queue")
		.unmarshal()
		.jacksonxml(CurrencyExchange.class)
		.to("log:received-message-from-active-mq-xml");
	}

}
