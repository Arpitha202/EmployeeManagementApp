package com.datajpa.relationship.dto;


import com.datajpa.relationship.dto.responseDto.DepartmentResponseDto;
import com.datajpa.relationship.dto.responseDto.EmployeeResponseDto;
import com.datajpa.relationship.dto.responseDto.ProjectResponseDto;

import com.datajpa.relationship.model.Department;
import com.datajpa.relationship.model.Employee;
import com.datajpa.relationship.model.Project;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static EmployeeResponseDto employeeToEmployeeResponseDto(Employee employee) {
    	EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
    	employeeResponseDto.setEmployeeId(employee.getEmployeeId());
    	employeeResponseDto.setFirstName(employee.getFirstName());
    	employeeResponseDto.setLastName(employee.getLastName());
    	employeeResponseDto.setBirthDate(employee.getBirthDate());
    	employeeResponseDto.setCellPhone(employee.getCellPhone());
       Department department=new Department();
       department.setDepartmentId(employee.getDepartment().getDepartmentId());
       department.setDepartmentName(employee.getDepartment().getDepartmentName());
       employeeResponseDto.setDepartment(department);
//    	employeeResponseDto.setDepartment(employee.getDepartment().getDepartmentName());
        List<Project> projectList = new ArrayList<>();
        List<Project> projects = employee.getProjects();
        for (Project project: projects) {
        	projectList.add(new Project(project.getId(), project.getProjectId(), project.getProjectName(),project.getProjectLocation()));
        	
        }
        employeeResponseDto.setProjects(projectList);
        return employeeResponseDto;
    }

    public static List<EmployeeResponseDto> employeeToEmployeeResponseDtos(List<Employee> employees) {
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        for (Employee employee: employees) {
        	employeeResponseDtos.add(employeeToEmployeeResponseDto(employee));
        }
        return employeeResponseDtos;
    }

    public static ProjectResponseDto projectToProjectResponseDto(Project project) {
    	ProjectResponseDto projectResponseDto = new ProjectResponseDto();
    	projectResponseDto.setId(project.getId());
    	projectResponseDto.setProjectId(project.getProjectId());
    	projectResponseDto.setProjectName(project.getProjectName());
    	projectResponseDto.setProjectLocation(project.getProjectLocation());
        List<Employee> employeeList = new ArrayList<>();
        List<Employee> employees = project.getEmployees();
        for (Employee employee: employees) {
        	employeeList.add(new Employee(employee.getEmployeeId(),employee.getFirstName(),
        			employee.getLastName(),employee.getBirthDate(),employee.getCellPhone(),employee.getDepartment()));
        }
        projectResponseDto.setEmployeeList(employeeList);
        return projectResponseDto;
    }

    public static List<ProjectResponseDto> projectToProjectResponseDtos(List<Project> projects){
        List<ProjectResponseDto> projectResponseDtos = new ArrayList<>();
        for (Project project: projects) {
        	projectResponseDtos.add(projectToProjectResponseDto(project));
        }
        return projectResponseDtos;
    }

    public static DepartmentResponseDto departmentToDepartmentResponseDto(Department department) {
    	DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
    	departmentResponseDto.setDepartmentId(department.getDepartmentId());
    	departmentResponseDto.setDepartmentName(department.getDepartmentName());
    	List<Employee> employeeList = new ArrayList<>();
        List<Employee> employees = department.getEmployees();
        for (Employee employee: employees) {
        	employeeList.add(new Employee(employee.getEmployeeId(),employee.getFirstName(),
        			employee.getLastName(),employee.getBirthDate(),employee.getCellPhone()));
        }
        departmentResponseDto.setEmployees(employeeList);
        return departmentResponseDto;
    }

    public static List<DepartmentResponseDto> departmentToDepartmentResponseDtos(List<Department> departments) {
        List<DepartmentResponseDto> departmentResponseDtos = new ArrayList<>();
        for (Department department: departments) {
        	departmentResponseDtos.add(departmentToDepartmentResponseDto(department));
        }
        return departmentResponseDtos;
    }
}
