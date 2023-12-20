package com.enviro.assessment.grad001.shubhamagarwaljain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestorRequest {

	@NotBlank(message ="Investor Id shouldn't  be null")
	private String investorId;
}
