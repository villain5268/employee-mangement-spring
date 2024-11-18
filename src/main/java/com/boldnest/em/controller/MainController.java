package com.boldnest.em.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/login")
	public String login() {
		return "login"; // Ensure the "login.html" view exists in the templates directory.
	}
}