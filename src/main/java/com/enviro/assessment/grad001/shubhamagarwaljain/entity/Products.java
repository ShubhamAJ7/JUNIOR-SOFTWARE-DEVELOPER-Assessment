package com.enviro.assessment.grad001.shubhamagarwaljain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer keyId;
	private String investorId;
	private String productId;
	private String productType;
	private String productName;
	private long currentBalance;
	
	

}
