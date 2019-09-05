package com.cognizant.assignments.casestudy.common;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ProjectVO {
	
	String projectName;
	String managerName;
	int priority;
	LocalDate startDate;
	LocalDate endDate;



}
