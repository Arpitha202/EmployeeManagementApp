package com.datajpa.relationship.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datajpa.relationship.dto.requestDto.EmployeeRequestDto;

import com.datajpa.relationship.dto.responseDto.EmployeeResponseDto;
import com.datajpa.relationship.model.Employee;
import com.datajpa.relationship.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("/add")
	public ResponseEntity<EmployeeResponseDto> addEmployee(@RequestBody final EmployeeRequestDto employeeRequestDto) {
		EmployeeResponseDto employeeResponseDto = employeeService.addEmployee(employeeRequestDto);
		return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable final Long id) {
		EmployeeResponseDto employeeResponseDto = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
	}

	@GetMapping("/getByName/{firstName}")
	public ResponseEntity<EmployeeResponseDto> getEmployeeByname(@PathVariable final String firstName) {
		EmployeeResponseDto employeeResponseDto = employeeService.getEmployeeByName(firstName);
		return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<EmployeeResponseDto>> getEmployees() {
		List<EmployeeResponseDto> employeeResponseDtos = employeeService.getEmployees();
		return new ResponseEntity<>(employeeResponseDtos, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<EmployeeResponseDto> deleteEmployee(@PathVariable final Long id) {
		EmployeeResponseDto employeeResponseDto = employeeService.deleteEmployee(id);
		return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<EmployeeResponseDto> editEmployee(@RequestBody final EmployeeRequestDto employeeRequestDto,
			@PathVariable final Long id) {
		EmployeeResponseDto employeeResponseDto = employeeService.editEmployee(id, employeeRequestDto);
		return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
	}

	@DeleteMapping("/removeProject/{id}/from/{employeeId}")
	public ResponseEntity<EmployeeResponseDto> removeProject(@PathVariable final Long id,
			@PathVariable final Long employeeId) {
		EmployeeResponseDto employeeResponseDto = employeeService.deleteProjectFromEmployee(employeeId, id);
		return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
	}

	@PostMapping("/addProject/{id}/to/{employeeId}")
	public ResponseEntity<EmployeeResponseDto> addproject(@PathVariable final Long id,
			@PathVariable final Long employeeId) {
		EmployeeResponseDto employeeResponseDto = employeeService.addProjectToEmployee(employeeId, id);
		return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
	}

	@PostMapping("/addDepartment/{departmentId}/to/{employeeId}")
	public ResponseEntity<EmployeeResponseDto> addDepartment(@PathVariable final Long departmentId,
			@PathVariable final Long employeeId) {
		EmployeeResponseDto employeeResponseDto = employeeService.addDepartmentToBook(employeeId, departmentId);
		return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
	}



    @DeleteMapping("/removeDepartment/{departmentId}/from/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> removeDepartemnt(@PathVariable final Long departmentId,
                                                          @PathVariable final Long employeeId) {
    	EmployeeResponseDto employeeResponseDto = employeeService.removeDepartmentFromBook(employeeId, departmentId);
        return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
    }

}
