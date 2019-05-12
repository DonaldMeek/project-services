package com.meek.donald.project;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.meek.donald.common.SerializationUtil;
import com.meek.donald.model.projects.Project;
import com.meek.donald.model.projects.ProjectModel;
import com.meek.donald.model.projects.ProjectTransformer;

@RestController
@RequestMapping("/service")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@PostMapping(value="/project/empl/id", consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getEmployeeIdsByProjectExample(@RequestBody String proj) {
		Project project = null;
		ProjectModel projModel = null;
		String projResponse = null;
		try {
			projModel = (ProjectModel) SerializationUtil.getBean(
					proj, ProjectModel.class);
			project = ProjectTransformer.transformProjectModel(projModel);
			project = projectService.getEmployeesByProjectId(project);
			projResponse = SerializationUtil.getJson(proj);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return projResponse;
				
	}
	
	@PostMapping(value="/project/all/active", consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllActiveProjects() {
		List<Project> projects = null;
		ProjectModel projModel = new ProjectModel();
		String projResponse = null;
		try {
			projects = projectService.getAllActiveProjects();
			projModel.setProjects(projects);
			projResponse = SerializationUtil.getJson(projModel);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return projResponse;
				
	}
	
	@PostMapping(value="/project/example", consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getProjectByExample(@RequestBody String proj) {
		Project project = null;
		ProjectModel projModel = null;
		String projResponse = null;
		try {
			projModel = (ProjectModel) SerializationUtil.getBean(
					proj, ProjectModel.class);
			project = ProjectTransformer.transformProjectModel(projModel);
			project = projectService.getProjectByExample(project);
			projResponse = SerializationUtil.getJson(proj);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return projResponse;
				
	}
}
