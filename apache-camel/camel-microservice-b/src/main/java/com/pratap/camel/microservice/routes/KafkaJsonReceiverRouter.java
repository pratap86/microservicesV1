package com.pratap.camel.microservice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaJsonReceiverRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("kafka:myKafkaTopic")
		.to("log:received-message-from-kafka-json");
	}

}
