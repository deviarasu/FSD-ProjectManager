package com.cognizant.assignments.casestudy.controller;

import com.cognizant.assignments.casestudy.entity.ParentTask;
import com.cognizant.assignments.casestudy.entity.Project;
import com.cognizant.assignments.casestudy.entity.Task;
import com.cognizant.assignments.casestudy.entity.User;
import com.cognizant.assignments.casestudy.repository.ParentTaskRepository;
import com.cognizant.assignments.casestudy.repository.ProjectRepository;
import com.cognizant.assignments.casestudy.repository.TaskRepository;
import com.cognizant.assignments.casestudy.repository.UserRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectManagerControllerTest {

	@MockBean
	ParentTaskRepository parentTaskRepo;

	@MockBean
	TaskRepository taskRepo;
	
	@MockBean
	UserRepository userRepo;
	
	@MockBean
	ProjectRepository projectRepo;

	@LocalServerPort
	int randomServerPort;

	Task task = new Task();

	ParentTask parentTask = new ParentTask();

	Project project = new Project();

	User user = new User();

	List<Task> taskList = new ArrayList<>();

	List<ParentTask> parentTaskList = new ArrayList<>();

	List<Project> projectList = new ArrayList<>();

	List<User> userList = new ArrayList<>();

	RestTemplate restTemplate = new RestTemplate();

	String baseUrl;

	@Before
	public void setUp() {

		baseUrl = "http://localhost:" + randomServerPort + "/api/pm/";

		parentTask.setParentTask("Parent Task1");

		task.setProjectId(123);
		task.setParentId(234);
		task.setTask("Task1");
		task.setStartDate(LocalDate.now());
		task.setEndDate(LocalDate.now());
		task.setPriority(12);
		task.setStatus("Completed");
		
		project.setProject("Project 123");
		project.setStartDate(LocalDate.now());
		project.setEndDate(LocalDate.now());
		project.setPriority(20);
		project.setManagerName("Manager Name123");
		
		user.setFirstName("AAAA");
		user.setLastName("bbb");
		user.setEmployeeId(123);

		taskList.add(task);

		parentTaskList.add(parentTask);
		
		projectList.add(project);
		
		userList.add(user);

	}

	// Task

	@Test
	public void TestFindTasksForProjectId() throws Exception {
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(taskRepo.findByProjectId(Mockito.anyInt())).thenReturn(taskList);
		Mockito.when(parentTaskRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(parentTask));

		ResponseEntity<String> result = restTemplate.getForEntity(uri + "findTasksForProjectId?projectId=2",
				String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Task1"));
	}
	
	@Test
	public void TestgetTaskById() throws Exception {
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(taskRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(task));

		ResponseEntity<String> result = restTemplate.getForEntity(uri + "getTaskById?taskId=2",
				String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Task1"));
	}

	@Test
	public void TestFindAllTasks() throws Exception {
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(taskRepo.findAll()).thenReturn(taskList);

		ResponseEntity<List<Task>> result = restTemplate.exchange(uri + "findAllTasks", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Task>>() {
				});

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(1, result.getBody().size());
	}
	
	@Test
	public void TestEndTaskForTaskId() throws Exception {
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(taskRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(task));
		Mockito.when(taskRepo.save(Mockito.any())).thenReturn(task);

		ResponseEntity<String> result = restTemplate.postForEntity(uri + "endTask?taskId=2", task, String.class);
		
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		
	}

	@Test
	public void TestInsertTask() throws Exception {
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(taskRepo.save(Mockito.any())).thenReturn(task);

		ResponseEntity<String> result = restTemplate.postForEntity(uri + "insertTask", task, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Task1"));
	}
	
	@Test
	public void TestUpdateTask() throws Exception {
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(taskRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(task));
		Mockito.when(taskRepo.save(Mockito.any())).thenReturn(task);

		ResponseEntity<String> result = restTemplate.postForEntity(uri + "updateTask?taskId=2", task, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	// Parent Task

	@Test
	public void TestFindAllParentTasks() throws Exception {
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(parentTaskRepo.findAll()).thenReturn(parentTaskList);

		ResponseEntity<List<ParentTask>> result = restTemplate.exchange(uri + "findAllParentTasks", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<ParentTask>>() {
				});

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(1, result.getBody().size());
	}

	@Test
	public void TestInsertParentTask() throws Exception {
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(parentTaskRepo.save(Mockito.any())).thenReturn(parentTask);

		ResponseEntity<String> result = restTemplate.postForEntity(uri + "insertParentTask?parentTask=parentTask1",
				task, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Parent Task1"));
	}

	@Test
	public void TestGetParentTaskForId() throws Exception {
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(parentTaskRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(parentTask));

		ResponseEntity<String> result = restTemplate.getForEntity(uri + "getParentTask?parentId=2", String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Parent Task1"));
	}

	// Project
	
	@Test
	public void TestInsertProject() throws Exception {
		
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(projectRepo.save(Mockito.any())).thenReturn(project);

		ResponseEntity<String> result = restTemplate.postForEntity(uri + "insertProject",
				project, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Project 123"));
		
	}
	
	@Test
	public void TestUpdateProject() throws Exception {

		URI uri = new URI(baseUrl);
		// when
		Mockito.when(projectRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(project));
		Mockito.when(projectRepo.save(Mockito.any())).thenReturn(project);

		ResponseEntity<String> result = restTemplate.postForEntity(uri + "updateProject?projectId=2",
				project, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Manager Name123"));
		
	}
	
	@Test
	public void TestFindAllProjects() throws Exception {
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(projectRepo.findAll()).thenReturn(projectList);

		ResponseEntity<List<Project>> result = restTemplate.exchange(uri + "findAllProjects", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Project>>() {
				});

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertNotEquals(2, result.getBody().size());
	}
	
	@Test
	public void TestDeleteProject() throws Exception {
		
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(projectRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(project));
		Mockito.doNothing().when(projectRepo).delete(project);

		restTemplate.delete(uri + "deleteProject?projectId=2");

		// Verify 
		Mockito.verify(projectRepo, Mockito.times(1)).delete(Mockito.any());
		
	}
	
	// User
	
	@Test
	public void TestInsertUser() throws Exception {
		
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);

		ResponseEntity<String> result = restTemplate.postForEntity(uri + "insertUser",
				user, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("AAAA"));
		
	}
	
	@Test
	public void TestUpdateUser() throws Exception {

		URI uri = new URI(baseUrl);
		// when
		Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);

		ResponseEntity<String> result = restTemplate.postForEntity(uri + "updateUser?userId=2",
				user, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("bbb"));
		
	}
	
	@Test
	public void TestFindAllUsers() throws Exception {
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(userRepo.findAll()).thenReturn(userList);

		ResponseEntity<List<User>> result = restTemplate.exchange(uri + "findAllUsers", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<User>>() {
				});

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertNotEquals(2, result.getBody().size());
	}
	
	@Test
	public void TestDeleteUser() throws Exception {
		
		URI uri = new URI(baseUrl);
		// when
		Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		Mockito.doNothing().when(userRepo).delete(user);

		restTemplate.delete(uri + "deleteUser?userId=2");

		// Verify 
		Mockito.verify(userRepo, Mockito.times(1)).delete(Mockito.any());
		
	}
}
