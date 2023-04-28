package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.requestDto.EmployeeRequestDto;

import com.datajpa.relationship.dto.responseDto.EmployeeResponseDto;

import com.datajpa.relationship.model.Employee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
	public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto);

	public EmployeeResponseDto getEmployeeById(Long employeeId);

	public EmployeeResponseDto getEmployeeByName(String firstName);

	public Employee getEmployee(Long employeeId);

	public Employee getEmployeeName(String firstName);

	public List<EmployeeResponseDto> getEmployees();

	public EmployeeResponseDto deleteEmployee(Long employeeId);

	public EmployeeResponseDto editEmployee(Long employeeId, EmployeeRequestDto employeeRequestDto);

	public EmployeeResponseDto addProjectToEmployee(Long employeeId, Long id);

	public EmployeeResponseDto deleteProjectFromEmployee(Long employeeId, Long id);

	public EmployeeResponseDto addDepartmentToBook(Long employeeId, Long departmentId);

	public EmployeeResponseDto removeDepartmentFromBook(Long employeeId, Long departmentId);

}
