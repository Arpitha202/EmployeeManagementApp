package com.datajpa.relationship.dto.responseDto;

import lombok.Data;

import java.util.List;

import com.datajpa.relationship.model.Employee;

@Data
public class ProjectResponseDto {
	private Long id;
	private String projectId;
	private String projectName;
	private String projectLocation;
	private List<Employee> employeeList;

}
