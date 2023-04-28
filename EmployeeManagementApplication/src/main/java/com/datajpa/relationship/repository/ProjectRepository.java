package com.datajpa.relationship.repository;



import com.datajpa.relationship.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	public Project findByProjectId(String projectId);
}
