package com.boldnest.em.service;

import com.boldnest.em.entity.Department;

import java.util.List;

public interface DepartmentService {
	List<Department> getAllDepartments();

	Department createDepartment(Department department);
}
