package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.requestDto.ProjectRequestDto;
import com.datajpa.relationship.dto.responseDto.ProjectResponseDto;
import com.datajpa.relationship.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

	private final ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@PostMapping("/addProject")
	public ResponseEntity<ProjectResponseDto> addProject(@RequestBody final ProjectRequestDto projectRequestDto) {
		ProjectResponseDto projectResponseDto = projectService.addProject(projectRequestDto);
		return new ResponseEntity<>(projectResponseDto, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ProjectResponseDto> getProject(@PathVariable final Long id) {
		ProjectResponseDto projectResponseDto = projectService.getProjectById(id);
		return new ResponseEntity<>(projectResponseDto, HttpStatus.OK);
	}
	
	@GetMapping("/getById/{pid}")
	public ResponseEntity<ProjectResponseDto> getProjectByName(@PathVariable final String pid) {
		ProjectResponseDto projectResponseDto = projectService.getProjectByProjectId(pid);
		return new ResponseEntity<>(projectResponseDto, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<ProjectResponseDto>> getProjects() {
		List<ProjectResponseDto> projectResponseDtos = projectService.getProjects();
		return new ResponseEntity<>(projectResponseDtos, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ProjectResponseDto> deleteProject(@PathVariable final Long id) {
		ProjectResponseDto projectResponseDto = projectService.deleteProject(id);
		return new ResponseEntity<>(projectResponseDto, HttpStatus.OK);
	}

	@PutMapping("/edit/{id}")
	private ResponseEntity<ProjectResponseDto> editProject(@PathVariable final Long id,
			@RequestBody final ProjectRequestDto projectRequestDto) {
		ProjectResponseDto projectResponseDto = projectService.editProject(id, projectRequestDto);
		return new ResponseEntity<>(projectResponseDto, HttpStatus.OK);
	}

}
