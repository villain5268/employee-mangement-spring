package com.boldnest.em.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boldnest.em.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByFirstNameContaining(String keyword);

	// Adjust query to compare `department.name` with the provided department name
	List<Employee> findByFirstNameContainingAndDepartment_Name(String keyword, String departmentName);

	List<Employee> findByDepartment_Name(String departmentName);
}
