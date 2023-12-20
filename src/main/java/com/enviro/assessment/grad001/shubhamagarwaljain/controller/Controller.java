package com.enviro.assessment.grad001.shubhamagarwaljain.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessment.grad001.shubhamagarwaljain.exception.InvestorNotFoundException;
import com.enviro.assessment.grad001.shubhamagarwaljain.exception.ProductNotFoundException;
import com.enviro.assessment.grad001.shubhamagarwaljain.exception.WithdrawalValidationException;
import com.enviro.assessment.grad001.shubhamagarwaljain.request.DownloadRequest;
import com.enviro.assessment.grad001.shubhamagarwaljain.request.InvestorRequest;
import com.enviro.assessment.grad001.shubhamagarwaljain.request.WithdrawalRequest;
import com.enviro.assessment.grad001.shubhamagarwaljain.response.InvestorDetails;
import com.enviro.assessment.grad001.shubhamagarwaljain.response.WithdrawalDetails;
import com.enviro.assessment.grad001.shubhamagarwaljain.service.InvestorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/invest")
public class Controller {

	@Autowired
	InvestorService investorService;

	//Retrieve Investor information with list of products 
	@GetMapping("/myDetails")
	public ResponseEntity<InvestorDetails> getDetails(@RequestBody @Valid InvestorRequest investorRequest)
			throws InvestorNotFoundException {
		return ResponseEntity.ok(investorService.getInnvestorDetails(investorRequest.getInvestorId()));

	}

	//Create withdrawal notice 
	@PostMapping("/withrawalNotice")
	public ResponseEntity<WithdrawalDetails> createWithdrawal(@RequestBody @Valid WithdrawalRequest withdrawalRequest)
			throws InvestorNotFoundException, ProductNotFoundException, WithdrawalValidationException {
		return new ResponseEntity<>(investorService.createWithdrawal(withdrawalRequest), HttpStatus.CREATED);
	}

	//Download statement for particular product and investor in date range provided 
	@GetMapping("/downloadStatement")
	public ResponseEntity<Resource> downloadStatement(@RequestBody @Valid DownloadRequest downloadRequest)
			throws IOException {
		InputStreamResource file = new InputStreamResource(investorService.downloadStatement(downloadRequest));
		System.out.println(downloadRequest);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"withdrawal_statement" + LocalDate.now() + ".csv\"")
				.contentType(MediaType.parseMediaType("application/csv")).body(file);

	}
}
