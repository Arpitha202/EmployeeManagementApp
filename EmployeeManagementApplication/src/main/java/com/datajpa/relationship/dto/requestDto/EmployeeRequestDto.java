package com.datajpa.relationship.dto.requestDto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeRequestDto {
	private String firstName;
	private String lastName;
	private String birthDate;
	private String cellPhone;
	private List<Long> projectIds;
	private Long departmentId;
}
