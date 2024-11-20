package com.boldnest.em.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boldnest.em.entity.Project;

@Service
public interface ProjectService {
	
	public List<Project> getAllProjects();
	
	public Project createProject(Project project);
	
	public Project getProjectById(Long id);

}
