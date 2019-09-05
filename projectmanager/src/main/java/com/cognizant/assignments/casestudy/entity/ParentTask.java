package com.cognizant.assignments.casestudy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PARENT_TASK")
public class ParentTask implements Serializable {

	private static final long serialVersionUID = -279394459515423554L;
	
	@Id
    @Column(name = "PARENT_ID", nullable = false, updatable = false)
	private int parentId;
	
	@Column(name = "PARENT_TASK", nullable = false)
    private String parentTask;

}
