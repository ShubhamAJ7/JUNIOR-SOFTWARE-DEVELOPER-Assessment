package com.enviro.assessment.grad001.shubhamagarwaljain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Withdrawals {

	// Entity used for creating withdrawal notice
	@Id
	@UuidGenerator
	private String withdrawalId;
	private LocalDateTime withdrawalDate;
	private String productId;
	private String investorId;
	private String accountNumber;
	private String bankName;
	private long withdrawlAmount;
	private long currentBalance;
	private long beforeBalance;
	
	
}
