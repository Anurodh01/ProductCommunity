package com.nagarro.productCom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productCom.service.StatsService;

import java.util.List;
import java.util.Map;

@RestController
public class StatsController {
	
	@Autowired
	private StatsService statsService;
	
	
	@GetMapping("/stats")
	public Map<String, Integer> getStats(){
		return this.statsService.getStats();
	}
}
