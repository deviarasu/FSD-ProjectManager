package com.cognizant.assignments.casestudy.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = -279394459515423554L;
	
	@Id
    @Column(name = "USER_ID", nullable = false, updatable = false)
	private int userId;
	
	@Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false)
    private String lastName;
	
	@Column(name = "EMPLOYEE_ID", nullable = false)
    private int employeeId;
	
	@Column(name = "PROJECT_ID")
    private int projectId;
	
	@Column(name = "TASK_ID")
    private int taskId;

}
