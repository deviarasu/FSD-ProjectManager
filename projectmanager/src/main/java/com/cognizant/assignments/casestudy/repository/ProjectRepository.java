package com.cognizant.assignments.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.assignments.casestudy.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
