package com.cognizant.assignments.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.assignments.casestudy.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByProjectId(int projectId);

}
