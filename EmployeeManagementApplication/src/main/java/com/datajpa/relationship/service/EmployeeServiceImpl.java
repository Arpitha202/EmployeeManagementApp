package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.Mapper;


import com.datajpa.relationship.dto.requestDto.EmployeeRequestDto;

import com.datajpa.relationship.dto.responseDto.EmployeeResponseDto;

import com.datajpa.relationship.model.Department;
import com.datajpa.relationship.model.Employee;
import com.datajpa.relationship.model.Project;
import com.datajpa.relationship.repository.EmployeeRepository;
import com.datajpa.relationship.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ProjectService projectService;
	private final DepartmentService departmentService;
	private final ProjectRepository projectRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectService projectService,
			DepartmentService departmentService, ProjectRepository projectRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.projectService = projectService;
		this.departmentService = departmentService;
		this.projectRepository = projectRepository;
	}

	
	@Override
	public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto) {
		Employee employee = new Employee();
		employee.setFirstName(employeeRequestDto.getFirstName());
		employee.setLastName(employeeRequestDto.getLastName());
		employee.setBirthDate(employeeRequestDto.getBirthDate());
		employee.setCellPhone(employeeRequestDto.getCellPhone());
		if (employeeRequestDto.getProjectIds().isEmpty()) {
			throw new IllegalArgumentException("you need atleast one project");
		} else {
			List<Project> projects = new ArrayList<>();
			for (Long projectId : employeeRequestDto.getProjectIds()) {
				Project project = projectService.getProject(projectId);
				projects.add(project);
			}
			employee.setProjects(projects);
		}
		if (employeeRequestDto.getDepartmentId() == null) {
			throw new IllegalArgumentException("you need atleast one department");
		}
		Department department = departmentService.getDepartment(employeeRequestDto.getDepartmentId());
		employee.setDepartment(department);
		Employee employee1 = employeeRepository.save(employee);
		return Mapper.employeeToEmployeeResponseDto(employee1);
	}

	@Override
	public EmployeeResponseDto getEmployeeById(Long employeeId) {
		Employee employee = getEmployee(employeeId);
		return Mapper.employeeToEmployeeResponseDto(employee);
	}

	@Override
	public EmployeeResponseDto getEmployeeByName(String firstName) {
		Employee employee = getEmployeeName(firstName);
		return Mapper.employeeToEmployeeResponseDto(employee);
	}

	@Override
	public Employee getEmployee(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new IllegalArgumentException("cannot find employee with id: " + employeeId));
		return employee;
	}

	@Override
	public Employee getEmployeeName(String firstName) {
		Employee employee = employeeRepository.findByFirstName(firstName);

		return employee;
	}

	@Override
	public List<EmployeeResponseDto> getEmployees() {
		List<Employee> employees = StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return Mapper.employeeToEmployeeResponseDtos(employees);
	}

	
	@Override
	public EmployeeResponseDto deleteEmployee(Long employeeId) {
		Employee employee = getEmployee(employeeId);
		employeeRepository.delete(employee);
		return Mapper.employeeToEmployeeResponseDto(employee);
	}

	
	@Override
	public EmployeeResponseDto editEmployee(Long employeeId, EmployeeRequestDto employeeRequestDto) {
		Employee employeeToEdit = getEmployee(employeeId);
		employeeToEdit.setFirstName(employeeRequestDto.getFirstName());
		employeeToEdit.setLastName(employeeRequestDto.getLastName());
		employeeToEdit.setBirthDate(employeeRequestDto.getBirthDate());
		employeeToEdit.setCellPhone(employeeRequestDto.getCellPhone());
		if (!employeeRequestDto.getProjectIds().isEmpty()) {

			List<Project> projects = new ArrayList<>();
			for (Long projectId : employeeRequestDto.getProjectIds()) {
				Project project = projectService.getProject(projectId);
				projects.add(project);
			}
			employeeToEdit.setProjects(projects);
		}
		if (employeeRequestDto.getDepartmentId() != null) {

			Department department = departmentService.getDepartment(employeeRequestDto.getDepartmentId());
			employeeToEdit.setDepartment(department);
		}
		return Mapper.employeeToEmployeeResponseDto(employeeToEdit);
	}

	@Override
	public EmployeeResponseDto addProjectToEmployee(Long employeeId, Long id) {
		Employee employee = getEmployee(employeeId);
		Project project = projectService.getProject(id);
		if (project.getEmployees().contains(project)) {
			throw new IllegalArgumentException("this project is already assign");
		}
		employee.addProject(project);
		project.addEmployee(employee);
		employeeRepository.save(employee);
		return Mapper.employeeToEmployeeResponseDto(employee);
	}

	@Override
	public EmployeeResponseDto deleteProjectFromEmployee(Long employeeId, Long id) {
		Employee employee = getEmployee(employeeId);
		Project project = projectService.getProject(id);
		if (!(project.getEmployees().contains(employee))) {
			throw new IllegalArgumentException("book does not have this author");
		}
		project.removeEmployee(employee);
		employee.deleteProject(project);
		employeeRepository.save(employee);
		return Mapper.employeeToEmployeeResponseDto(employee);
	}

	@Override
	public EmployeeResponseDto addDepartmentToBook(Long employeeId, Long departmentId) {
		Employee employee = getEmployee(employeeId);
		Department department = departmentService.getDepartment(departmentId);
		if (Objects.nonNull(employee.getDepartment())) {
			throw new IllegalArgumentException("book already has a catogory");
		}
		employee.setDepartment(department);
		department.addEmployee(employee);
		employeeRepository.save(employee);
		return Mapper.employeeToEmployeeResponseDto(employee);
	}

	@Override
	public EmployeeResponseDto removeDepartmentFromBook(Long employeeId, Long departmentId) {
		Employee employee = getEmployee(employeeId);
		Department department = departmentService.getDepartment(departmentId);
		if (!(Objects.nonNull(employee.getDepartment()))) {
			throw new IllegalArgumentException("book does not have a category to delete");
		}
		employee.setDepartment(null);
		department.removeEmployee(employee);
		employeeRepository.save(employee);
		return Mapper.employeeToEmployeeResponseDto(employee);
	}

}
