package com.cognizant.assignments.casestudy.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TASK")
public class Task implements Serializable {

	private static final long serialVersionUID = -279394459515423554L;
	
	@Id
    @Column(name = "TASK_ID", nullable = false, updatable = false)
	private int taskId;
	
	@Column(name = "PARENT_ID", nullable = false)
    private int parentId;
	
	@Column(name = "PROJECT_ID", nullable = false)
    private int projectId;
	
	@Column(name = "TASK", nullable = false)
    private String task;
	
	@Column(name = "START_DATE", nullable = false)
	private LocalDate startDate;
	
	@Column(name = "END_DATE", nullable = false)
	private LocalDate endDate;
	
	@Column(name = "PRIORITY", nullable = false)
    private int priority;
	
	@Column(name = "STATUS", nullable = false)
    private String status;	

}
