package com.boldnest.em.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boldnest.em.entity.Project;
import com.boldnest.em.service.ProjectService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping
	public String getAllProjects(Model model) {
		model.addAttribute("projects", projectService.getAllProjects());
		return "projects";
	}

	@GetMapping("/new")
	public String showNewProjectForm(Model model) {
		model.addAttribute("project", new Project());
		return "new_project";
	}

	@PostMapping
	public String createProject(@ModelAttribute("project") @Valid Project project, BindingResult result) {
		if (result.hasErrors()) {
			return "new_project";
		}
		projectService.createProject(project);
		return "redirect:/projects";
	}

	@GetMapping("/{id}")
	public String viewProjectDetails(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectService.getProjectById(id));
		return "project_details";
	}
}
