package com.boldnest.em.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boldnest.em.entity.Employee;
import com.boldnest.em.repository.EmployeeRepository;
import com.boldnest.em.repository.PerformanceReviewRepository;
import com.boldnest.em.service.EmployeeService;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PerformanceReviewRepository reviewRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}

	@Override
	public long getEmployeeCount() {
		return employeeRepository.count();
	}

	@Override
	public double getAverageEmployeeAge() {
		return employeeRepository.findAll().stream().mapToInt(Employee::getAge).average().orElse(0);
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		reviewRepository.deleteByEmployeeId(id);
		this.employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> searchEmployees(String keyword, String departmentName) {
		if (keyword != null && departmentName != null) {
			return employeeRepository.findByFirstNameContainingAndDepartment_Name(keyword, departmentName);
		} else if (keyword != null) {
			return employeeRepository.findByFirstNameContaining(keyword);
		} else if (departmentName != null) {
			return employeeRepository.findByDepartment_Name(departmentName);
		} else {
			return employeeRepository.findAll();
		}
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.employeeRepository.findAll(pageable);
	}

	@Override
	public void exportEmployeesToExcel(HttpServletResponse response) throws IOException {
		List<Employee> employees = employeeRepository.findAll();

		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Employees");

			// Create header row
			Row headerRow = sheet.createRow(0);
			String[] headers = { "ID", "First Name", "Last Name", "Email", "Department", "Age", "Position" };
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(createHeaderCellStyle(workbook));
			}

			// Write employee data
			int rowNum = 1;
			for (Employee employee : employees) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(employee.getId());
				row.createCell(1).setCellValue(employee.getFirstName() != null ? employee.getFirstName() : "N/A");
				row.createCell(2).setCellValue(employee.getLastName() != null ? employee.getLastName() : "N/A");
				row.createCell(3).setCellValue(employee.getEmail() != null ? employee.getEmail() : "N/A");
				row.createCell(4)
						.setCellValue(employee.getDepartment() != null ? employee.getDepartment().getName() : "N/A");
				row.createCell(5).setCellValue(employee.getAge());
				row.createCell(6).setCellValue(employee.getPosition() != null ? employee.getPosition() : "N/A");
			}

			// Set response headers
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");

			// Write workbook to output stream
			workbook.write(response.getOutputStream());
		}
	}

	private CellStyle createHeaderCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		return style;
	}

	@Override
	public Employee findEmployeeByEmail(String email) {
		return employeeRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
	}

	@Override
	public List<Employee> searchEmployees(String keyword, String departmentName, Integer minAge, Integer maxAge,
			Double minSalary, Double maxSalary) {
		return employeeRepository.searchEmployees(keyword, departmentName, minAge, maxAge, minSalary, maxSalary);
	}
}
