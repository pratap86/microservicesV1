package com.pratap.microservices.currencyexchange.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
	@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	public String getSampleApi() {
		LOGGER.info("Sample api call received");
		ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-api/",
				String.class);
		return responseEntity.getBody();
	}

	public String hardcodedResponse(Exception e) {
		return "fallback-responce";
	}

}
