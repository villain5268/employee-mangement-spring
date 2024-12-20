package com.boldnest.em.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boldnest.em.entity.Department;
import com.boldnest.em.entity.Employee;
import com.boldnest.em.service.AttendanceService;
import com.boldnest.em.service.DepartmentService;
import com.boldnest.em.service.EmployeeService;
import com.boldnest.em.service.PerformanceReviewService;
import com.boldnest.em.service.ProjectService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private PerformanceReviewService performanceReviewService;

	@PostMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/index";
	}

	@GetMapping("/employee/{id}")
	public String viewEmployeeDashboard(@PathVariable Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "employee_dashboard";
	}

	@GetMapping("/index")
	public String viewHomePage(Model model) {
		model.addAttribute("departments", departmentService.getAllDepartments());
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		model.addAttribute("projectsCount", projectService.getAllProjects().size());
		model.addAttribute("reviewsCount", performanceReviewService.getAllReviews().size());
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		List<Department> departments = departmentService.getAllDepartments();
		if (departments.isEmpty()) {
			System.out.println("No departments found!");
		}
		model.addAttribute("employee", new Employee());
		model.addAttribute("departments", departments);
		return "new_employee";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		model.addAttribute("departments", departmentService.getAllDepartments());
		return "new_employee"; // Reuses the same form for editing
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("departments", departmentService.getAllDepartments());
			return "new_employee"; // Corrected to use the appropriate form template name
		}

		if (employee.getStatus() == null || employee.getStatus().isBlank()) {
			employee.setStatus("Active");
		}

		employeeService.saveEmployee(employee);

		return "redirect:/index";
	}

	@GetMapping("/employees/search")
	public String searchEmployees(@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "department", required = false) String department, Model model) {
		List<Employee> employees = employeeService.searchEmployees(keyword, department);
		model.addAttribute("listEmployees", employees);
		return "index";
	}

	@GetMapping("/employees/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		employeeService.exportEmployeesToExcel(response);
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam(value = "sortField", defaultValue = "firstName") String sortField,
			@RequestParam(value = "sortDir", defaultValue = "asc") String sortDir, Model model) {
		int pageSize = 5;

		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", Math.max(page.getTotalPages(), 1));
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listEmployees", listEmployees);

		// Avoid overwriting attributes passed from `viewHomePage`
		return "index";
	}
}
