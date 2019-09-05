package com.cognizant.assignments.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.assignments.casestudy.entity.ParentTask;

public interface ParentTaskRepository extends JpaRepository<ParentTask, Integer> {
	
	

}
