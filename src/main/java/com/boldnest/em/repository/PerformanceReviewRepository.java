package com.boldnest.em.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boldnest.em.entity.PerformanceReview;

import jakarta.transaction.Transactional;

@Repository
public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {

	List<PerformanceReview> findByEmployeeId(Long employeeId);

	@Transactional
	@Modifying
	@Query("DELETE FROM PerformanceReview pr WHERE pr.employee.id = :employeeId")
	void deleteByEmployeeId(@Param("employeeId") long employeeId);

}
