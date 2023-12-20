package com.enviro.assessment.grad001.shubhamagarwaljain.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalRequest {
	
	//Data Requested while creation of withdrawal notice
	@NotBlank(message ="Product Id shouldn't be null")
	private String productId;
	@NotBlank(message ="Investor Id shouldn't  be null")
	private String investorId;
	@NotBlank(message ="Bank account number shouldn't be null")
	private String accountNumber;
	@NotBlank(message ="Bank name shouldn't be null")
	private String bankName;
	@NotNull(message ="withdrawAmmount shouldn't be null")
	private long withdrawalAmount;

}
