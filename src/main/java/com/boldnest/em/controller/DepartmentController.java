package com.boldnest.em.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boldnest.em.entity.Department;
import com.boldnest.em.service.DepartmentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping
	public String getAllDepartments(Model model) {
		List<Department> departments = departmentService.getAllDepartments();
		model.addAttribute("departments", departments);
		return "departments";
	}

	@GetMapping("/new")
	public String showNewDepartmentForm(Model model) {
		model.addAttribute("department", new Department());
		return "new_department";
	}

	@PostMapping
	public String createDepartment(@ModelAttribute("department") @Valid Department department, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "new_department"; // Return form if there are validation errors
		}
		departmentService.createDepartment(department);
		return "redirect:/departments";
	}
}
