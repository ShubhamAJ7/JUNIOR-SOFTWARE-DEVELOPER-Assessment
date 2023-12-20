package com.enviro.assessment.grad001.shubhamagarwaljain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enviro.assessment.grad001.shubhamagarwaljain.entity.Investors;

@Repository
public interface InvestorsRepository extends JpaRepository<Investors, String>{
	
	@Query(value="select TOP 1 * from investors where investor_Id =:investorId", nativeQuery=true)
	public Investors findByInvestorId(@Param("investorId") String investorId);
	
	

}
