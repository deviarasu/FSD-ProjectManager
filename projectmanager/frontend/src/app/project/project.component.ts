import { Component, OnInit } from '@angular/core';
import {DatePipe, formatDate} from '@angular/common';
import {UserService} from "../services/user/user.service";
import {ProjectService} from "../services/project/project.service";
import {Project} from "../services/project/project";
import { OrderPipe } from 'ngx-order-pipe';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  userList;
  projectList;
  manager;
  projectName;
  priority = 0;
  startDate;
  endDate;
  showStartEndDate;
  isEdit = false;
  projectId;
  managerNameError = false;

  constructor(private userService: UserService,
              private projectService: ProjectService,
              private orderPipe: OrderPipe) { }

  ngOnInit() {
    this.getAllUsers();
    this.getAllProjects();
    this.setStartEndDates();
  }

  getAllUsers() {

    this.userService.getAllUsers().subscribe(val => {this.userList = val;});
  }

  getAllProjects() {

    this.projectService.getAllProjects().subscribe(val => {this.projectList = val;});
  }

  setStartEndDates() {

    var currentDate = new Date();
    this.startDate = formatDate(currentDate, 'yyyy-MM-dd', 'en')

    currentDate.setDate(currentDate.getDate()+1);
    this.endDate = formatDate(currentDate, 'yyyy-MM-dd', 'en');
  }

  updateManagerOnScreen(user) {
    this.manager = user.firstName + ' ' + user.lastName;
    this.managerNameError = false;
  }

  saveProject(form) {

    if (!this.manager || this.manager === '') {
      this.managerNameError = true;
    } else {

      let project = new Project(this.projectName, this.manager, this.priority,
        this.startDate, this.endDate);

      if (this.projectId > 0) {
        this.projectService.updateProject(project, this.projectId).subscribe(
          (data) => {
            console.log("Successfully updated Project");
            form.resetForm();
            this.isEdit = false;
            this.getAllProjects();
            this.setStartEndDates();
          },
          error => {
            console.error("Error while adding user")
          }
        );

      } else {

        this.projectService.addProject(project).subscribe(
          (data) => {
            form.resetForm();
            this.getAllProjects();
            this.setStartEndDates();
          },
          error => {
            console.error("Error while adding user")
          }
        );
      }
    }
  }



  deleteProject(project) {

  this.projectService.deleteProject(project.projectId).subscribe(
    (data) => {
      console.log("Successfully deleted User");
      this.getAllProjects();
    }
  );
}

  updateScreenChange(project) {
    console.log(project);

    if(this.isEdit) {
      this.isEdit = false;
      this.projectName = this.manager = this.projectId = '';
      this.priority = 0;
      this.showStartEndDate = false;
      this.setStartEndDates();
    } else {
      this.showStartEndDate = true;
      this.projectName = project.project;
      this.manager = project.managerName;
      this.priority = project.priority;
      this.startDate = this.getDate(project.startDate);
      this.endDate = this.getDate(project.endDate);
      this.isEdit = true;
      this.projectId = project.projectId;

    }

  }

  sortBy(type: string) {
    if(type === 'startDate') {
      this.projectList = this.orderPipe.transform(this.projectList, 'startDate');
    } else if(type === 'endDate') {
      this.projectList = this.orderPipe.transform(this.projectList, 'endDate');
    }
  }

  getDate(inDate: any) {

    var d = new Date();
    d.setDate(inDate[2]);
    d.setMonth(inDate[1]-1);
    d.setFullYear(inDate[0]);
    return formatDate(d, 'yyyy-MM-dd', 'en');

  }
}
