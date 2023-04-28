package com.datajpa.relationship.dto.responseDto;

import lombok.Data;

import java.util.List;

import com.datajpa.relationship.model.Employee;

@Data
public class DepartmentResponseDto {
	private Long departmentId;
	private String departmentName;
	private List<Employee> employees;
}
