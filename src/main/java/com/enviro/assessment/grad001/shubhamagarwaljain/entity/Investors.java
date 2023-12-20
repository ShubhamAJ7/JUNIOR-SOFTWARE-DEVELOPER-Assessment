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
public class Investors {
	
	// Entity used for investors data
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String investorId;
	private String name;
	private int age;
	private String street;
	private String city;
	private String state;
	private String country;	
	private int postalCode;
	private long mobileNumber;
	private String emailId;
	
	
}
