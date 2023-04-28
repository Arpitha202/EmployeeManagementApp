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
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
	@SequenceGenerator(name = "project_id_seq", sequenceName = "project_id_seq", allocationSize =1, initialValue=2000)
	private Long id;
	private String projectId;
	private String projectName;
	private String projectLocation;

	@ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employees = new ArrayList<>();

	

	public Project(String projectName, String projectLocation, List<Employee> employees) {
		super();
		this.projectName = projectName;
		this.projectLocation = projectLocation;
		this.employees = employees;
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}

	public Project(String projectId, String projectName, String projectLocation) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectLocation = projectLocation;
	}

	public Project(Long id, String projectId, String projectName, String projectLocation) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectLocation = projectLocation;
	}
	
	

}
