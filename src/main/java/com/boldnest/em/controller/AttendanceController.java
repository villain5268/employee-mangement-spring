package com.boldnest.em.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boldnest.em.entity.Attendance;
import com.boldnest.em.entity.Employee;
import com.boldnest.em.service.AttendanceService;
import com.boldnest.em.service.EmployeeService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public String viewAttendancePage(Model model) {
		// Fetch all attendance records and add them to the model
		model.addAttribute("attendanceRecords", attendanceService.getAllAttendanceRecords());
		return "attendance"; // Thymeleaf template "attendance.html"
	}

	// View Attendance Dashboard for an Employee
	@GetMapping("/employee/{id}/attendance")
	public String viewEmployeeAttendance(@PathVariable Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		model.addAttribute("attendanceRecords", attendanceService.getAttendanceByEmployeeId(id));
		return "employee_attendance";
	}

	// Show Form to Mark Attendance
	@GetMapping("/attendance/new/{id}")
	public String showAttendanceForm(@PathVariable Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		model.addAttribute("attendance", new Attendance());
		return "new_attendance";
	}

	// Handle Attendance Submission
	@PostMapping("/attendance")
	public String markAttendance(@ModelAttribute("attendance") Attendance attendance) {
		attendanceService.saveAttendance(attendance);
		return "redirect:/employee/" + attendance.getEmployee().getId() + "/attendance";
	}

}
