package com.enviro.assessment.grad001.shubhamagarwaljain.response;

import java.util.List;

import com.enviro.assessment.grad001.shubhamagarwaljain.entity.Investors;
import com.enviro.assessment.grad001.shubhamagarwaljain.entity.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestorDetails {
	
	public Investors investor;
	public List<Products> productList;

}
