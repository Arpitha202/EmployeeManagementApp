package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.Mapper;


import com.datajpa.relationship.dto.requestDto.ProjectRequestDto;

import com.datajpa.relationship.dto.responseDto.ProjectResponseDto;

import com.datajpa.relationship.model.Project;

import com.datajpa.relationship.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;

	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;

	}


	@Override
	public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
		Project project = new Project();
		project.setProjectId(projectRequestDto.getProjectId());
		project.setProjectName(projectRequestDto.getProjectName());
		project.setProjectLocation(projectRequestDto.getProjectLocation());
		projectRepository.save(project);
		return Mapper.projectToProjectResponseDto(project);
	}

	@Override
	
	public List<ProjectResponseDto> getProjects() {
		List<Project> projects = StreamSupport.stream(projectRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return Mapper.projectToProjectResponseDtos(projects);
	}

	@Override
	public ProjectResponseDto getProjectById(Long id) {

		return Mapper.projectToProjectResponseDto(getProject(id));
	}
	
	@Override
	public ProjectResponseDto getProjectByProjectId(String id) {
		// TODO Auto-generated method stub
		return Mapper.projectToProjectResponseDto(getProject(id));
	}
	
	@Override
	public Project getProject(String id) {
		Project project = projectRepository.findByProjectId(id);
		return project;
	}

	@Override
	public Project getProject(Long id) {
		Project project = projectRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("project with id: " + id + " could not be found"));
		return project;
	}

	@Override
	public ProjectResponseDto deleteProject(Long id) {
		Project project = getProject(id);
		projectRepository.delete(project);

		return Mapper.projectToProjectResponseDto(project);
	}

	
	@Override
	public ProjectResponseDto editProject(Long id, ProjectRequestDto projectRequestDto) {
		Project projectToEdit = getProject(id);
		projectToEdit.setProjectId(projectRequestDto.getProjectId());
		projectToEdit.setProjectName(projectRequestDto.getProjectName());
		projectToEdit.setProjectLocation(projectRequestDto.getProjectLocation());
		return Mapper.projectToProjectResponseDto(projectToEdit);
	}



}
