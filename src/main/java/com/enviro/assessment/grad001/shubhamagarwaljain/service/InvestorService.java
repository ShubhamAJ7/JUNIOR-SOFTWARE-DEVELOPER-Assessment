package com.enviro.assessment.grad001.shubhamagarwaljain.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.enviro.assessment.grad001.shubhamagarwaljain.exception.InvestorNotFoundException;
import com.enviro.assessment.grad001.shubhamagarwaljain.exception.ProductNotFoundException;
import com.enviro.assessment.grad001.shubhamagarwaljain.exception.WithdrawalValidationException;
import com.enviro.assessment.grad001.shubhamagarwaljain.request.DownloadRequest;
import com.enviro.assessment.grad001.shubhamagarwaljain.request.WithdrawalRequest;
import com.enviro.assessment.grad001.shubhamagarwaljain.response.InvestorDetails;
import com.enviro.assessment.grad001.shubhamagarwaljain.response.WithdrawalDetails;

public interface InvestorService {

	public InvestorDetails getInnvestorDetails(String investorId) throws InvestorNotFoundException;

	public WithdrawalDetails createWithdrawal(WithdrawalRequest withdrawalRequest)
			throws InvestorNotFoundException, ProductNotFoundException, WithdrawalValidationException;

	public ByteArrayInputStream downloadStatement(DownloadRequest downloadRequest) throws IOException;

}
