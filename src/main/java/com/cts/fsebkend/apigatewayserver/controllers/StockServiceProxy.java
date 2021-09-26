package com.cts.fsebkend.apigatewayserver.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.fsebkend.apigatewayserver.models.Stock;

@Configuration
@FeignClient(name="stock-service", url = "${LOAD_BALANCER_URL}")
public interface StockServiceProxy {
	
	@GetMapping("/api/v1.0/market/stock/getall")
	public ResponseEntity<?> getAllStocks();

	@PostMapping("/api/v1.0/market/stock/add/{companycode}")
	public ResponseEntity<?> addStock(@RequestBody Stock stock , @PathVariable("companycode") String companyCode);
	
	@GetMapping("/api/v1.0/market/stock/get/{companycode}/{startdate}/{enddate}")
	public ResponseEntity<?> getStocksByCompanyCodeAndStartDateEndDate(@PathVariable("companycode") String companyCode,
			@PathVariable("startdate") String startDate,
			@PathVariable("enddate") String endDate);
}
