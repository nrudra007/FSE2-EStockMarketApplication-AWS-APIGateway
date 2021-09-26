package com.cts.fsebkend.apigatewayserver.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.fsebkend.apigatewayserver.models.Company;


@Configuration
@FeignClient(name="company-service", url = "${LOAD_BALANCER_URL}")
public interface CompanyServiceProxy {

	@GetMapping("/api/v1.0/market/company/getall")
	public ResponseEntity<?> getAllCompanies();
	
	@PostMapping("/api/v1.0/market/company/register")
	public ResponseEntity<?> registerCompany(@RequestBody Company company);
	
	@GetMapping("/api/v1.0/market/company/info/{companycode}")
	public ResponseEntity<?> getCompanyByCompanyCode(@PathVariable("companycode") String companyCode);
	
	@GetMapping("/api/v1.0/market/company/delete/{companycode}")
	public ResponseEntity<String> deleteCompanyByCompanyCode(@PathVariable("companycode") String companyCode);
}
