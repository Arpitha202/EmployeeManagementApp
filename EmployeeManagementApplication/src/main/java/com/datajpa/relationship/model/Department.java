package com.datajpa.relationship.model;

import lombok.Data;

import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_seq")
	@SequenceGenerator(name = "department_id_seq", sequenceName = "department_id_seq", allocationSize = 1, initialValue = 1)
	private Long departmentId;
	private String departmentName;
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employees = new ArrayList<>();

	public Department(String departmentName, List<Employee> employees) {
		super();
		this.departmentName = departmentName;
		this.employees = employees;
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}

}
