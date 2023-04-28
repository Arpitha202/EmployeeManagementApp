package com.datajpa.relationship.model;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
	@SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize =1, initialValue=1000)
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String cellPhone;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    public Employee(String firstName, String lastName, String birthDate, String cellPhone, List<Project> projects,
			Department department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.cellPhone = cellPhone;
		this.projects = projects;
		this.department = department;
	}
    
	public Employee(String firstName, String lastName, String birthDate, String cellPhone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.cellPhone = cellPhone;
	}
	

    public void addProject(Project project) {
    	projects.add(project);
    }

    public void deleteProject(Project project) {
    	projects.remove(project);
    }

	public Employee(Long employeeId, String firstName, String lastName, String birthDate, String cellPhone) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.cellPhone = cellPhone;
	}

	public Employee(Long employeeId, String firstName, String lastName, String birthDate, String cellPhone,
			Department department) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.cellPhone = cellPhone;
		this.department = department;
	}


	


	


}
