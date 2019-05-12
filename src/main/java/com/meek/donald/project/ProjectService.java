package com.meek.donald.project;

import java.util.List;

import com.meek.donald.model.projects.Project;

public interface ProjectService {

	Project getEmployeesByProjectId(Project project);
	List<Project> getAllActiveProjects();
	Project getProjectByExample(Project project);
}
