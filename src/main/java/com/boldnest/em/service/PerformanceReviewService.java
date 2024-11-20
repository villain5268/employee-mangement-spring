package com.boldnest.em.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boldnest.em.entity.PerformanceReview;

@Service
public interface PerformanceReviewService {

	List<PerformanceReview> getAllReviews();

	PerformanceReview getReviewById(Long id);

	PerformanceReview addReview(PerformanceReview review);

	List<PerformanceReview> getReviewsByEmployeeId(Long employeeId);

	void savePerformanceReview(PerformanceReview review);
	


}
