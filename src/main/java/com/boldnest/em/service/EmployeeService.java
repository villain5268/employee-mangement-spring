package com.boldnest.em.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.boldnest.em.entity.Employee;

import jakarta.servlet.http.HttpServletResponse;

@Service
public interface EmployeeService {
	List<Employee> getAllEmployees();

	void saveEmployee(Employee employee);

	Employee getEmployeeById(long id);

	void deleteEmployeeById(long id);

	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	List<Employee> searchEmployees(String keyword, String department);

	double getAverageEmployeeAge();

	long getEmployeeCount();

	void exportEmployeesToExcel(HttpServletResponse response) throws IOException; // New method
}
