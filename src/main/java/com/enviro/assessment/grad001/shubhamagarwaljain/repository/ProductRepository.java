package com.enviro.assessment.grad001.shubhamagarwaljain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enviro.assessment.grad001.shubhamagarwaljain.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Integer>{
	
	// Retrieve list of products from repository for investor_id provided
	@Query(value="select * from products where investor_Id =:investorId ", nativeQuery=true)
	public List<Products> findProductsByInvestorId(@Param("investorId") String investorId);
	
	// Retrieve product details from repository for provided product_id and investor_id 
	@Query(value="select TOP 1 * from products where investor_Id =:investorId and product_Id =:productId ", nativeQuery=true)
	public Products findProductsByInvestorIdandProductId(@Param("investorId") String investorId, @Param("productId") String productId);

	
	// Update current balance in repository for provided product_id and investor_id during withdrawal
	@Modifying
	@Query(value="Update products SET current_Balance =:updated_Balance where investor_Id =:investorId and product_Id =:productId ", nativeQuery=true)
	public void updateProductsByInvestorIdandProductId(@Param("updated_Balance") long updated_Balance, @Param("investorId") String investorId, @Param("productId") String productId);
}
