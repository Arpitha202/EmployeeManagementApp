package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.requestDto.DepartmentRequestDto;

import com.datajpa.relationship.dto.responseDto.DepartmentResponseDto;

import com.datajpa.relationship.model.Department;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
	public Department getDepartment(Long departmentId);

	public DepartmentResponseDto addDepartment(DepartmentRequestDto departmentRequestDto);

	public DepartmentResponseDto getDepartmentById(Long departmentId);

	public List<DepartmentResponseDto> getDepartments();

	public DepartmentResponseDto deleteDepartment(Long departmentId);

	public DepartmentResponseDto editDepartment(Long departmentId, DepartmentRequestDto departmentRequestDto);
}
