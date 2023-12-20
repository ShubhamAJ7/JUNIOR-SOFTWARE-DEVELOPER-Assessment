package com.enviro.assessment.grad001.shubhamagarwaljain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalDetails {

	//Return response after successful withdrawal creation(Notification to investor)
	
	private String investorName;
	private String productName;
	private String accountNumber;
	private String bankName;
	private long withdrawlAmount;
	private long currentBalance;
	private long beforeBalance;

}
