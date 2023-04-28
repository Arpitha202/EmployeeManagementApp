package com.datajpa.relationship.dto.responseDto;

import lombok.Data;

import java.util.List;

import com.datajpa.relationship.model.Department;
import com.datajpa.relationship.model.Project;

@Data
public class EmployeeResponseDto {
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String cellPhone;

	private List<Project> projects;

	private Department department;
}
