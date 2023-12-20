package com.enviro.assessment.grad001.shubhamagarwaljain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enviro.assessment.grad001.shubhamagarwaljain.entity.Withdrawals;

public interface WithdrawalRepository extends JpaRepository<Withdrawals, String>{
	
	@Query(value ="SELECT * FROM WITHDRAWALS WHERE INVESTOR_ID =:investor_ID AND PRODUCT_ID =:product_Id AND WITHDRAWAL_DATE BETWEEN :fromDate AND :toDate ", nativeQuery=true)
	public List<Withdrawals> findDataBetweenDates(@Param("investor_ID") String investor_Id, @Param("product_Id") String product_Id, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
}
