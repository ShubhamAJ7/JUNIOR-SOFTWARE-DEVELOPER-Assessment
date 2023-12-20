package com.enviro.assessment.grad001.shubhamagarwaljain.request;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadRequest {
	
	@NotBlank(message ="Product Id shouldn't be null")
	private String productId;
	@NotBlank(message ="Investor Id shouldn't  be null")
	private String investorId;
	@NotNull(message ="fromDate shouldn't  be null")
	@PastOrPresent(message ="fromDate must not after present date")
	private LocalDate fromDate;
	@FutureOrPresent(message = "toDate must not after present date")
	@NotNull(message ="toDate shouldn't  be null")
	private LocalDate toDate;

}
