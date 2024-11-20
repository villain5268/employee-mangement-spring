package com.boldnest.em.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boldnest.em.dto.UserRegistrationDto;
import com.boldnest.em.entity.User;
import com.boldnest.em.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/profile")
	public String viewProfile(Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		model.addAttribute("user", user);
		return "profile";
	}

	@PostMapping("/profile")
	public String updateProfile(@ModelAttribute("user") User user, Principal principal) {
		userService.updateUserProfile(principal.getName(), user);
		return "redirect:/profile?success";
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto(null, null, null, null);
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto registrationDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registration";
		}
		try {
			userService.save(registrationDto);
			return "redirect:/registration?success";
		} catch (Exception e) {
			// Log the exception for debugging
			logger.error("Error during user registration", e);
			model.addAttribute("errorMessage", "An error occurred while processing your registration.");
			return "registration";
		}
	}

}
