package com.boldnest.em.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boldnest.em.entity.Project;
import com.boldnest.em.repository.ProjectRepository;
import com.boldnest.em.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public Project createProject(Project project) {
		return projectRepository.save(project);
	}

	public Project getProjectById(Long id) {
		return projectRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Project not found with ID: " + id));
	}

}
