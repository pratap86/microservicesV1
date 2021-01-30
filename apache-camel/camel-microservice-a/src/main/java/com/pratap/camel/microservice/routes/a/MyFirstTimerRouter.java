package com.pratap.camel.microservice.routes.a;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MyFirstTimerRouter extends RouteBuilder {
	

	@Autowired
	private GetCurrentTimeBean getCurrentTimeBean;
	
	@Autowired
	private SimpleLoggingProcessingComponent simpleLoggingProcessingComponent;

	@Override
	public void configure() throws Exception {

		// timer
		// transformation
		// log
		from("timer:first-timer")
		.log("${body}")
		//Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
		.transform().constant("Time is now : "+LocalDateTime.now())
		.log("${body}")
		// Processing
		// Transformation
		.bean(getCurrentTimeBean, "getCurrentTime")
		.log("${body}")
		.bean(simpleLoggingProcessingComponent, "process")
		.log("${body}")
		.process(new SimpleLoggingProcessor())
		.to("log:first-timer");
	}

}

@Component
class GetCurrentTimeBean{
	
	public String getCurrentTime() {
		return "Time is now : "+LocalDateTime.now();
	}
}

@Component
class SimpleLoggingProcessingComponent{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
	
	public void process(String message) {
		LOGGER.info("SimpleLoggingProcessingComponent {}", message);
	}
}

class SimpleLoggingProcessor implements Processor {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleLoggingProcessor.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
	}

}
