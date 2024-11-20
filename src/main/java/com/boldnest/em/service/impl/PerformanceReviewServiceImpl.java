package com.boldnest.em.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boldnest.em.entity.PerformanceReview;
import com.boldnest.em.repository.PerformanceReviewRepository;
import com.boldnest.em.service.PerformanceReviewService;

@Service
public class PerformanceReviewServiceImpl implements PerformanceReviewService {

	@Autowired
	private PerformanceReviewRepository reviewRepository;

	@Override
	public List<PerformanceReview> getAllReviews() {
		return reviewRepository.findAll();
	}

	@Override
	public PerformanceReview getReviewById(Long id) {
		return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found with ID: " + id));
	}

	@Override
	public PerformanceReview addReview(PerformanceReview review) {
		return reviewRepository.save(review);
	}

	@Override
	public List<PerformanceReview> getReviewsByEmployeeId(Long employeeId) {
		return reviewRepository.findByEmployeeId(employeeId);
	}

	@Override
	public void savePerformanceReview(PerformanceReview review) {
		reviewRepository.save(review);
	}
}
