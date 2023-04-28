package com.datajpa.relationship.service;


import com.datajpa.relationship.dto.requestDto.ProjectRequestDto;


import com.datajpa.relationship.dto.responseDto.ProjectResponseDto;

import com.datajpa.relationship.model.Project;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto);
    public List<ProjectResponseDto> getProjects();
    public ProjectResponseDto getProjectById(Long id);
    public ProjectResponseDto getProjectByProjectId(String id);
    public Project getProject(Long id);
    public Project getProject(String id);
    public ProjectResponseDto deleteProject(Long id);
    public ProjectResponseDto editProject(Long id, ProjectRequestDto projectRequestDto);
 
   
}
