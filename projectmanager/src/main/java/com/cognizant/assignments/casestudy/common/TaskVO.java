package com.cognizant.assignments.casestudy.common;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TaskVO {

	int taskId;
	int projectId;
	int parentId;
	String parentName;
	String taskName;
	LocalDate startDate;
	LocalDate endDate;
	int priority;
	String status;
}
