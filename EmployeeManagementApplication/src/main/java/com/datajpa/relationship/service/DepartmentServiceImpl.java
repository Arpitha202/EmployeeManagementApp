package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.Mapper;


import com.datajpa.relationship.dto.requestDto.DepartmentRequestDto;

import com.datajpa.relationship.dto.responseDto.DepartmentResponseDto;

import com.datajpa.relationship.model.Department;

import com.datajpa.relationship.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Department getDepartment(Long departmentId) {

		return departmentRepository.findById(departmentId)
				.orElseThrow(() -> new IllegalArgumentException("could not find department with id: " + departmentId));
	}

	@Override
	public DepartmentResponseDto addDepartment(DepartmentRequestDto departmentRequestDto) {
		Department department = new Department();
		department.setDepartmentName(departmentRequestDto.getDepartmentName());
		departmentRepository.save(department);
		return Mapper.departmentToDepartmentResponseDto(department);
	}

	@Override
	public DepartmentResponseDto getDepartmentById(Long departmentId) {
		Department department = getDepartment(departmentId);

		return Mapper.departmentToDepartmentResponseDto(department);
	}

	@Override
	public List<DepartmentResponseDto> getDepartments() {
		List<Department> departments = StreamSupport.stream(departmentRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return Mapper.departmentToDepartmentResponseDtos(departments);
	}

	@Override
	public DepartmentResponseDto deleteDepartment(Long departmentId) {
		Department department = getDepartment(departmentId);
		departmentRepository.delete(department);
		return Mapper.departmentToDepartmentResponseDto(department);
	}

	
	@Override
	public DepartmentResponseDto editDepartment(Long departmentId, DepartmentRequestDto departmentRequestDto) {
		Department departmentToEdit = getDepartment(departmentId);
		departmentToEdit.setDepartmentName(departmentRequestDto.getDepartmentName());

		return Mapper.departmentToDepartmentResponseDto(departmentToEdit);
	}

}
