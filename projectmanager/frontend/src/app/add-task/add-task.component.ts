import { Component, OnInit } from '@angular/core';
import {formatDate} from '@angular/common';
import {ProjectService} from "../services/project/project.service";
import {UserService} from "../services/user/user.service";
import {TaskService} from "../services/task/task.service";
import {Task} from "../services/task/task";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {

  projectList;
  userList;
  parentTaskList
  projectName;
  parentTaskName;
  userName;
  taskName;
  priority=0;
  isParentTask;
  projectNameError = false;
  parentTaskError = false;
  userNameError = false;
  startDate;
  endDate;
  projectId;
  parentId;
  taskId;
  isEdit = false;
  updateTaskID;

  constructor(private projectService: ProjectService,
              private userService: UserService,
              private taskService: TaskService,
              private route: ActivatedRoute) {

    this.getAllProjects();
    this.getAllParentTasks();

    this.route.params.subscribe( params => {
      if (params['type']) {
        this.updateTaskID = params['type'];
        this.getUpdateTask();
      }});

  }

  ngOnInit() {
    this.getAllUsers()
    this.setStartEndDates();

  }

  getUpdateTask() {

      this.taskService.getTaskById(this.updateTaskID).subscribe(
        val => {
      this.taskName = val.task;
      this.startDate = this.getDate(val.startDate);
      this.endDate = this.getDate(val.endDate);
      this.priority = val.priority
        for(let pTask of this.parentTaskList) {
          if (pTask.parentId === val.parentId) {
            this.updateParentTaskOnScreen(pTask);
          }
        }

        for(let proj of this.projectList) {
          if (proj.projectId === val.projectId) {
            this.updateProjectOnScreen(proj);
          }
        }
          this.isEdit = true;
        });
  }
  updateScreenChange(form) {
    if(this.isEdit) {
      this.isEdit = false;
    }
    form.resetForm();
    this.setStartEndDates();
  }


  getAllUsers() {

    this.userService.getAllUsers().subscribe(val => {this.userList = val;});
  }

  getAllProjects() {

    this.projectService.getAllProjects().subscribe(val => {this.projectList = val;});
  }

  getAllParentTasks() {
    this.taskService.getAllParentTasks().subscribe(val => {this.parentTaskList = val;});
  }

  setStartEndDates() {
console.log("here");
    var currentDate = new Date();
    var endDate = new Date();
    this.startDate = formatDate(currentDate, 'yyyy-MM-dd', 'en')

    endDate.setDate(currentDate.getDate()+1);
    this.endDate = formatDate(endDate, 'yyyy-MM-dd', 'en');
  }

  updateProjectOnScreen(project) {
    this.projectName = project.project;
    this.projectId = project.projectId;
    this.projectNameError = false;
  }

  updateParentTaskOnScreen(parentTask) {
    this.parentTaskName = parentTask.parentTask;
    this.parentId = parentTask.parentId;
    this.parentTaskError = false;
  }

  updateUserOnScreen(user) {
    this.userName = user.firstName + ' ' + user.lastName;
    this.userNameError = false;
  }

  getDate(inDate: any) {

    return formatDate(inDate, 'yyyy-MM-dd', 'en');

  }

  saveTask(form) {
    var valid = true;

    if (this.isParentTask) {

      this.taskService.addParentTask(this.taskName).subscribe(
        (data) => { console.log("Successfully added Parent Task");
          form.resetForm();
          this.getAllParentTasks();
        },
        error => {console.error("Error while adding user")}

      );
    } else {
      if(!this.projectName || this.projectName === '') {
        this.projectNameError = true;
        valid = false;
      }
      if (!this.parentTaskName || this.parentTaskName === '') {
        this.parentTaskError = true;
        valid = false;
      }
      if (!this.userName || this.userName === '') {
        this.userNameError = true;
        valid = false;
      }
      if(valid) {

        var task = new Task(this.projectId, this.parentId, this.taskName, this.startDate, this.endDate, this.priority);

        if(this.updateTaskID > 0){

          this.taskService.update(task, this.updateTaskID).subscribe(
            (data) => {
              form.resetForm();
              this.isEdit = false;
              this.setStartEndDates();
            },
            error => {
              console.error("Error while updating task")
            }
          );


        } else {
          this.taskService.addTask(task).subscribe(
            (data) => {
              form.resetForm();
              this.setStartEndDates();
            },
            error => {
              console.error("Error while adding task")
            }
          );
        }


      }

    }

  }



}
