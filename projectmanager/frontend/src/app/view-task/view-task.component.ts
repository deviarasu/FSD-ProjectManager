import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../services/project/project.service";
import {TaskService} from "../services/task/task.service";
import {formatDate} from "@angular/common";
import { OrderPipe } from 'ngx-order-pipe';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit {

  projectList;
  projectName;
  taskList;
  projectId;

  constructor(private projectService: ProjectService,
              private taskService: TaskService,
              private orderPipe: OrderPipe,
              private router: Router) { }

  ngOnInit() {

    this.getAllProjects();
  }

  getAllProjects() {

    this.projectService.getAllProjects().subscribe(val => {this.projectList = val;});
  }

  updateProjectOnScreen(project) {
    this.projectName = project.project;
    this.projectId = project.projectId;
    this.getTasks();
  }

  getTasks() {

    this.taskService.getTasksForProjectId(this.projectId).subscribe(val => {this.taskList = val;
    console.log(this.taskList)});

  }

  getDate(inDate: any) {

    var d = new Date();
    d.setDate(inDate[2]);
    d.setMonth(inDate[1]-1);
    d.setFullYear(inDate[0]);
    return formatDate(d, 'yyyy-MM-dd', 'en');;

  }

  sortBy(type: string) {

    this.taskList = this.orderPipe.transform(this.taskList, type);
  }

  endTask(task) {

    this.taskService.endTaskForTaskId(task.taskId).subscribe(val => {this.getTasks()});
  }

  isTaskCompleted(task) {
    return task.status === 'Completed';
  }

  callViewTask(task) {

    this.router.navigate(['addTask', task.taskId]);
  }
}
