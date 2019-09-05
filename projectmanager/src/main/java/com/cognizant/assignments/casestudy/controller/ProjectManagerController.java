package com.cognizant.assignments.casestudy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.cognizant.assignments.casestudy.common.ProjectVO;
import com.cognizant.assignments.casestudy.common.TaskVO;
import com.cognizant.assignments.casestudy.common.UserVO;
import com.cognizant.assignments.casestudy.entity.ParentTask;
import com.cognizant.assignments.casestudy.entity.Project;
import com.cognizant.assignments.casestudy.entity.Task;
import com.cognizant.assignments.casestudy.entity.User;
import com.cognizant.assignments.casestudy.service.ProjectService;
import com.cognizant.assignments.casestudy.service.TaskService;
import com.cognizant.assignments.casestudy.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/pm/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectManagerController {
	
	TaskService taskService;
	UserService userService;
	ProjectService projectService;      
    
    // Parent Task

    @RequestMapping(value = "insertParentTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ParentTask insertParentTask(@RequestParam(value = "parentTask") String parentTask) {

        return taskService.insertParentTask(parentTask);
    }

    @RequestMapping(value = "findAllParentTasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ParentTask> findAllParentTasks() {

        return taskService.findAllParentTasks();
    }

    @RequestMapping(value = "getParentTask", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<ParentTask> getParentTask(@RequestParam(value = "parentId") int parentId) {

        return taskService.getByParentId(parentId);
    }

    // Task

    @RequestMapping(value = "insertTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task insertTask(@RequestBody TaskVO taskVO) {

        return taskService.insertTask(taskVO);
    }

    @RequestMapping(value = "updateTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task updateTask(@RequestParam(value = "taskId") int taskId, @RequestBody TaskVO taskVo) {

        return taskService.updateTask(taskVo, taskId);
    }


    @RequestMapping(value = "findAllTasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> findAllTasks() {

        return taskService.findAllTasks();
    }

    @RequestMapping(value = "endTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task endTaskForTaskId(@RequestParam(value = "taskId") int taskId) {

        return taskService.endTask(taskId);
    }

    @RequestMapping(value = "getTaskById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task getTaskById(@RequestParam(value = "taskId") int taskId) {

        return taskService.getTaskById(taskId);
    }

    @RequestMapping(value = "findTasksForProjectId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskVO> findTasksForProjectId(@RequestParam(value = "projectId") int projectId) {

        List<Task> taskListFromDB = taskService.findTasksForProjectId(projectId);
        List<TaskVO> taskList = new ArrayList<>();

        for(Task task : taskListFromDB) {
            TaskVO taskVo = new TaskVO();
            taskVo.setTaskId(task.getTaskId());
            taskVo.setTaskName(task.getTask());
            taskVo.setParentName(taskService.getParentName(task.getParentId()));
            taskVo.setStartDate(task.getStartDate());
            taskVo.setEndDate(task.getEndDate());
            taskVo.setPriority(task.getPriority());
            taskVo.setStatus(task.getStatus());
            taskList.add(taskVo);
        }

        return taskList;
    }


    // Project

    @RequestMapping(value = "insertProject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project insertProject(@RequestBody ProjectVO projectVO) {

        return projectService.insertProject(projectVO);
    }

    @RequestMapping(value = "updateProject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project upadateProject(@RequestParam(value = "projectId") int projectId, @RequestBody ProjectVO projectVO) {

        return projectService.updateProject(projectVO, projectId);
    }

    @RequestMapping(value = "findAllProjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> findAllProjects() {

        return projectService.findAllProjects();
    }

    @RequestMapping(value = "deleteProject", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProject(@RequestParam(value = "projectId") int projectId) {

        projectService.deleteProject(projectId);
    }

    // User

    @RequestMapping(value = "insertUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User insertUser(@RequestBody UserVO userVO) {

        return userService.insertUser(userVO);
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestParam(value = "userId") int userId, @RequestBody UserVO userVO) {

        return userService.updateUser(userVO, userId);
    }

    @RequestMapping(value = "findAllUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAllUsers() {

        return userService.findAllUsers();
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@RequestParam(value = "userId") int userId) {

        userService.deleteUser(userId);
    }



}
