package com.cts.fsebkend.apigatewayserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cts.fsebkend.apigatewayserver.models.AddCompanyResponse;
import com.cts.fsebkend.apigatewayserver.models.Company;

@RestController
@RequestMapping("/e-stock-market/api/v1.0/market/company")
public class CompanyServiceProxyController {
	
	Logger log = LoggerFactory.getLogger(CompanyServiceProxyController.class);
	
	@Autowired
	private CompanyServiceProxy companyServiceProxy;

	@GetMapping("/getall")
	public ResponseEntity<?> getAllCompanies() {
		log.debug("******within CompanyServiceProxyController.getAllCompanies()");
		ResponseEntity<?> resp = null;
		try {
			resp = companyServiceProxy.getAllCompanies();
		}catch(Exception ex) {
			log.error("An exception occurred while fetching all the company details from company-service >> "+ex.getMessage());
		}
		return resp;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerCompany(@RequestBody Company company) {
		log.debug("******within CompanyServiceProxyController.registerCompany()");
		try {
			ResponseEntity<?> resp = companyServiceProxy.registerCompany(company);
			return resp;
		}catch(Exception ex) {
			log.error("An exception occurred while registering company details via company-service >> "+ex.getMessage());
			AddCompanyResponse addCompanyResponse = new AddCompanyResponse();
			if(ex instanceof ResponseStatusException) {
				log.error("Company registration fails; failure reason is: "+((ResponseStatusException) ex).getReason());
				addCompanyResponse.setErrorMsg("Company registration fails due to "+((ResponseStatusException) ex).getReason());
				return new ResponseEntity<>(addCompanyResponse, HttpStatus.BAD_REQUEST);
			}
			log.error("An error occurred while registering company in E-Stock Market Portal >> "+ex.getMessage());
			addCompanyResponse.setErrorMsg("An error occurred while registering company in E-Stock Market Portal!!");
			return new ResponseEntity<>(addCompanyResponse, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@GetMapping("/info/{companycode}")
	public ResponseEntity<?> getCompanyByCompanyCode(@PathVariable("companycode") String companyCode) {
		return companyServiceProxy.getCompanyByCompanyCode(companyCode);
	}
	
	@GetMapping("/delete/{companycode}")
	public ResponseEntity<String> deleteCompanyByCompanyCode(@PathVariable("companycode") String companyCode) {
		return companyServiceProxy.deleteCompanyByCompanyCode(companyCode);
	}
}
