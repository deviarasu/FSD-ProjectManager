package com.cognizant.assignments.casestudy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.assignments.casestudy.common.ProjectVO;
import com.cognizant.assignments.casestudy.entity.Project;
import com.cognizant.assignments.casestudy.repository.ProjectRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectService {
	
	ProjectRepository projectRepo;

	public Project insertProject(ProjectVO projectVO) {

		Project projectEntity = new Project();
		projectEntity.setProject(projectVO.getProjectName());
		projectEntity.setStartDate(projectVO.getStartDate());
		projectEntity.setEndDate(projectVO.getEndDate());
		projectEntity.setPriority(projectVO.getPriority());
		projectEntity.setManagerName(projectVO.getManagerName());
		
		return projectRepo.save(projectEntity);
	}

	public List<Project> findAllProjects() {
		
		return projectRepo.findAll();
	}

	public void deleteProject(int projectId) {

		Project projectEntity = projectRepo.findById(projectId).orElseThrow();
		projectRepo.delete(projectEntity);
	}


	public Project updateProject(ProjectVO projectVO, int projectId) {

		Project projectEntity = projectRepo.findById(projectId).orElseThrow();
		System.out.println(projectEntity);

		projectEntity.setProject(projectVO.getProjectName());
		projectEntity.setStartDate(projectVO.getStartDate());
		projectEntity.setEndDate(projectVO.getEndDate());
		projectEntity.setPriority(projectVO.getPriority());
		projectEntity.setManagerName(projectVO.getManagerName());

		return projectRepo.save(projectEntity);
    }
}
