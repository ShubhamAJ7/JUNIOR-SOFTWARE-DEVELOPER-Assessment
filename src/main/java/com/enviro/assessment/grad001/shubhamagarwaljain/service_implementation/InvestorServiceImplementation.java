package com.enviro.assessment.grad001.shubhamagarwaljain.service_implementation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enviro.assessment.grad001.shubhamagarwaljain.entity.Investors;
import com.enviro.assessment.grad001.shubhamagarwaljain.entity.Products;
import com.enviro.assessment.grad001.shubhamagarwaljain.entity.Withdrawals;
import com.enviro.assessment.grad001.shubhamagarwaljain.exception.InvestorNotFoundException;
import com.enviro.assessment.grad001.shubhamagarwaljain.exception.ProductNotFoundException;
import com.enviro.assessment.grad001.shubhamagarwaljain.exception.WithdrawalValidationException;
import com.enviro.assessment.grad001.shubhamagarwaljain.repository.InvestorsRepository;
import com.enviro.assessment.grad001.shubhamagarwaljain.repository.ProductRepository;
import com.enviro.assessment.grad001.shubhamagarwaljain.repository.WithdrawalRepository;
import com.enviro.assessment.grad001.shubhamagarwaljain.request.DownloadRequest;
import com.enviro.assessment.grad001.shubhamagarwaljain.request.WithdrawalRequest;
import com.enviro.assessment.grad001.shubhamagarwaljain.response.InvestorDetails;
import com.enviro.assessment.grad001.shubhamagarwaljain.response.WithdrawalDetails;
import com.enviro.assessment.grad001.shubhamagarwaljain.service.InvestorService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InvestorServiceImplementation implements InvestorService {

	@Autowired
	InvestorsRepository investorsRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	WithdrawalRepository withdrawalRepository;

	// Retrieving Investor Details
	@Override
	public InvestorDetails getInnvestorDetails(String investorId) throws InvestorNotFoundException {

		InvestorDetails investorDetails = new InvestorDetails();

		if (investorsRepository.findByInvestorId(investorId) != null) {
			investorDetails.setInvestor(investorsRepository.findByInvestorId(investorId));
		} else {
			throw new InvestorNotFoundException("Investor not found with Id : " + investorId);
		}

		investorDetails.setProductList(productRepository.findProductsByInvestorId(investorId));

		return investorDetails;
	}

	// Creating withdrawal notice
	@Override
	public WithdrawalDetails createWithdrawal(WithdrawalRequest withdrawalRequest)
			throws InvestorNotFoundException, ProductNotFoundException, WithdrawalValidationException {
		Withdrawals withdrawal = new Withdrawals();
		Products product = productRepository.findProductsByInvestorIdandProductId(withdrawalRequest.getInvestorId(),
				withdrawalRequest.getProductId());
		Investors investor = investorsRepository.findByInvestorId(withdrawalRequest.getInvestorId());

		//Validating investor id
		if (investor == null) {
			throw new InvestorNotFoundException("Investor not found with Id : " + withdrawalRequest.getInvestorId());
		}
		//Validating product id
		if (product == null) {
			throw new ProductNotFoundException("Product not found with Id : " + withdrawalRequest.getProductId());
		}

		//Validating that for product type RETIREMENT age must be greater than 65
		if (product.getProductType().equalsIgnoreCase("RETIREMENT") && investor.getAge() < 65) {
			throw new WithdrawalValidationException("For Product type RETIREMENT age must be greater than 65");
		}
		//Validating withdrawal amount must be less current balance
		if (withdrawalRequest.getWithdrawalAmount() > product.getCurrentBalance()) {
			throw new WithdrawalValidationException("Withdrawal amount must be less current balance ");
		}
		//validating withdrawal amount must not be more than 90% of current balance
		if (withdrawalRequest.getWithdrawalAmount() > product.getCurrentBalance() * 0.9) {
			throw new WithdrawalValidationException("Withdrawal amount must not be more than 90% of current balance");
		}

		long updatedAmount = product.getCurrentBalance() - withdrawalRequest.getWithdrawalAmount();

		
		withdrawal.setInvestorId(withdrawalRequest.getInvestorId());
		withdrawal.setProductId(withdrawalRequest.getProductId());
		withdrawal.setWithdrawlAmount(withdrawalRequest.getWithdrawalAmount());
		withdrawal.setBeforeBalance(product.getCurrentBalance());
		withdrawal.setCurrentBalance(updatedAmount);
		withdrawal.setWithdrawalDate(LocalDateTime.now());
		withdrawal.setAccountNumber(withdrawalRequest.getAccountNumber());
		withdrawal.setBankName(withdrawalRequest.getBankName());
		
		//Updating withdrawal repository with withdrawal request
		withdrawalRepository.save(withdrawal);

		//Updating current balance in product repository 
		productRepository.updateProductsByInvestorIdandProductId(updatedAmount, withdrawalRequest.getInvestorId(),
				withdrawalRequest.getProductId());

		//Developing response notification for withdrawal request
		WithdrawalDetails withdrawDeatils = new WithdrawalDetails(investor.getName(), product.getProductName(),
				withdrawalRequest.getAccountNumber(), withdrawalRequest.getBankName(),
				withdrawalRequest.getWithdrawalAmount(), updatedAmount, withdrawal.getBeforeBalance());

		return withdrawDeatils;

	}

	// Download statement for specific product and investor in date range provided
	@Override
	public ByteArrayInputStream downloadStatement(DownloadRequest downloadRequest) throws IOException {

		//Setting headers for CSV file
		final String[] HEADERS = { "Withdrwaral_Id", "Withdrawal_Date", "Product_Id", "Investor_Id", "Account_Number",
				"Bank_Name", "Withdrwal_Amount", "Current_Balace", "Before_Balance" };
		final CSVFormat format = CSVFormat.DEFAULT.withHeader(HEADERS);

		try {
			//Retrieving withdrawal data
			List<Withdrawals> withdrawalList = withdrawalRepository.findDataBetweenDates(
					downloadRequest.getInvestorId(), downloadRequest.getProductId(), downloadRequest.getFromDate(),
					downloadRequest.getToDate());

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			CSVPrinter printer = new CSVPrinter(new PrintWriter(out), format);
			printer.printRecord(withdrawalList);

			printer.flush();
			printer.close();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to export csv file" + e.getMessage());
		}

	}

}
