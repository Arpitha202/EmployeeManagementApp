package com.datajpa.relationship.repository;

import com.datajpa.relationship.model.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//	Employee findByFirstName(String firstName);
	
	@Query("select e from #{#entityName} e where e.firstName = ?1")
	Employee findByFirstName(String firstName);
}
