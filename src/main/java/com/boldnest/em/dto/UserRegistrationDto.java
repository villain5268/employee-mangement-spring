package com.boldnest.em.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

	@NotBlank(message = "First name is required")
	@Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
	private String firstName;

	@NotBlank(message = "Last name is required")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Password is required") 
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;
}
