package com.cts.fsebkend.apigatewayserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fsebkend.apigatewayserver.models.Stock;

@RestController
@RequestMapping("/e-stock-market/api/v1.0/market/stock")
public class StockServiceProxyController {
	
	Logger log = LoggerFactory.getLogger(StockServiceProxyController.class);
	
	@Autowired
	private StockServiceProxy stockServiceProxy;

	@GetMapping("/getall")
	public ResponseEntity<?> getAllStocks() {
		log.debug("******within StockServiceProxyController.getAllStocks()");
		ResponseEntity<?> resp = null;
		try {
			resp = stockServiceProxy.getAllStocks();
		}catch(Exception ex) {
			log.error("An exception occurred while fetching all the stock details from stock-service >> "+ex.getMessage());
		}
		return resp;
	}
	
	@PostMapping("/add/{companycode}")
	public ResponseEntity<?> addStock(
			@RequestBody Stock stock , @PathVariable("companycode") String companyCode ) {
		log.debug("******within StockServiceProxyController.addStock()");
		ResponseEntity<?> resp = null;
		try {
			resp = stockServiceProxy.addStock(stock, companyCode);
		}catch(Exception ex) {
			log.error("An exception occurred while adding a stock via stock-service >> "+ex.getMessage());
		}
		return resp;
	}
	
	@GetMapping("/get/{companycode}/{startdate}/{enddate}")
	public ResponseEntity<?> getStocksByCompanyCodeAndStartDateEndDate(@PathVariable("companycode") String companyCode,
			@PathVariable("startdate") String startDate,
			@PathVariable("enddate") String endDate) {
		return stockServiceProxy.getStocksByCompanyCodeAndStartDateEndDate(companyCode, startDate, endDate);
	}
}