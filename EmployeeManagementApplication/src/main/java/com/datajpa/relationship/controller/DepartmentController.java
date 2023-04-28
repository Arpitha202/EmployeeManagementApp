package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.requestDto.DepartmentRequestDto;

import com.datajpa.relationship.dto.responseDto.DepartmentResponseDto;

import com.datajpa.relationship.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@PostMapping("/add")
	public ResponseEntity<DepartmentResponseDto> addDepartment(
			@RequestBody final DepartmentRequestDto categoryRequestDto) {
		DepartmentResponseDto categoryResponseDto = departmentService.addDepartment(categoryRequestDto);
		return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<DepartmentResponseDto> getDepartment(@PathVariable final Long id) {
		DepartmentResponseDto departmentResponseDto = departmentService.getDepartmentById(id);
		return new ResponseEntity<>(departmentResponseDto, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<DepartmentResponseDto>> getDepartments() {
		List<DepartmentResponseDto> departmentResponseDtos = departmentService.getDepartments();
		return new ResponseEntity<>(departmentResponseDtos, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<DepartmentResponseDto> deleteDepartment(@PathVariable final Long id) {
		DepartmentResponseDto departmentResponseDto = departmentService.deleteDepartment(id);
		return new ResponseEntity<>(departmentResponseDto, HttpStatus.OK);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<DepartmentResponseDto> editDepartment(
			@RequestBody final DepartmentRequestDto categoryRequestDto, @PathVariable final Long id) {
		DepartmentResponseDto departmentResponseDto = departmentService.editDepartment(id, categoryRequestDto);
		return new ResponseEntity<>(departmentResponseDto, HttpStatus.OK);
	}

}
