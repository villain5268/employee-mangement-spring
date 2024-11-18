package com.boldnest.em.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boldnest.em.entity.Department;
import com.boldnest.em.repository.DepartmentRepository;
import com.boldnest.em.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}
}
