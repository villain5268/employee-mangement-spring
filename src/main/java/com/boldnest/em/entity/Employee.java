package com.boldnest.em.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	// Additional fields for filtering
	private int age;
	private String position;

	@Column(name = "phone_number")
	private String phoneNumber;

	private String address;

	@Column(name = "date_of_joining")
	private LocalDateTime dateOfJoining;

	private double salary;

	@Column(name = "status", nullable = false)
	private String status; // Active, Inactive, Terminated
}
