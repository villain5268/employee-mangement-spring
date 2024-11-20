package com.boldnest.em.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boldnest.em.entity.Employee;
import com.boldnest.em.entity.PerformanceReview;
import com.boldnest.em.service.EmployeeService;
import com.boldnest.em.service.PerformanceReviewService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/performance-reviews")
public class PerformanceReviewController {

	@Autowired
	private PerformanceReviewService performanceReviewService;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public String getAllReviews(Model model) {
		model.addAttribute("reviews", performanceReviewService.getAllReviews());
		return "performance_reviews";
	}

	@GetMapping("/new/{id}")
	public String showNewPerformanceReviewForm(@PathVariable Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			throw new RuntimeException("Employee not found with ID: " + id);
		}
		model.addAttribute("employee", employee);
		model.addAttribute("performanceReview", new PerformanceReview());
		return "new_review";
	}

	@PostMapping("/new/{id}")
	public String addPerformanceReview(@PathVariable Long id,
			@ModelAttribute("performanceReview") @Valid PerformanceReview performanceReview, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			Employee employee = employeeService.getEmployeeById(id);
			model.addAttribute("employee", employee);
			return "new_review";
		}
		Employee employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			throw new RuntimeException("Employee not found with ID: " + id);
		}
		performanceReview.setEmployee(employee);
		performanceReviewService.savePerformanceReview(performanceReview);
		return "redirect:/performance-reviews/employee/" + id + "/reviews";
	}

	@GetMapping("/employee/{id}/reviews")
	public String viewEmployeeReviews(@PathVariable Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			throw new RuntimeException("Employee not found with ID: " + id);
		}
		model.addAttribute("employee", employee);
		model.addAttribute("reviews", performanceReviewService.getReviewsByEmployeeId(id));
		return "employee_reviews";
	}
}
