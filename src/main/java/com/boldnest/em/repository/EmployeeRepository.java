package com.boldnest.em.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boldnest.em.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByFirstNameContaining(String keyword);

	// Adjust query to compare `department.name` with the provided department name
	List<Employee> findByFirstNameContainingAndDepartment_Name(String keyword, String departmentName);

	List<Employee> findByDepartment_Name(String departmentName);

	Optional<Employee> findByEmail(String email);

	@Query("SELECT e FROM Employee e WHERE "
			+ "(:keyword IS NULL OR LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) "
			+ "OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
			+ "AND (:departmentName IS NULL OR e.department.name = :departmentName) "
			+ "AND (:minAge IS NULL OR e.age >= :minAge) " + "AND (:maxAge IS NULL OR e.age <= :maxAge) "
			+ "AND (:minSalary IS NULL OR e.salary >= :minSalary) "
			+ "AND (:maxSalary IS NULL OR e.salary <= :maxSalary)")
	List<Employee> searchEmployees(@Param("keyword") String keyword, @Param("departmentName") String departmentName,
			@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge, @Param("minSalary") Double minSalary,
			@Param("maxSalary") Double maxSalary);
}