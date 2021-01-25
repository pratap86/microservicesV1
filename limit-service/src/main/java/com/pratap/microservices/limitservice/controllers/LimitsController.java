package com.pratap.microservices.limitservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratap.microservices.limitservice.configuration.LimitsServiceConfig;
import com.pratap.microservices.limitservice.models.Limits;

@RestController
public class LimitsController {

	@Autowired
	private LimitsServiceConfig limitsServiceConfig;
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(limitsServiceConfig.getMinimum(), limitsServiceConfig.getMaximum());
	}
}
