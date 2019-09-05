import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ParentTask} from "./parentTask";
import {Task} from 'src/app/services/task/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  endpoint = 'http://localhost:8736/api/pm/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };


  constructor(private  httpClient:  HttpClient) { }

  addParentTask(taskName: string) {

    return this.httpClient.post<any>(this.endpoint + 'insertParentTask?parentTask=' + taskName, this.httpOptions);
  }

  getAllParentTasks() : Observable<ParentTask[]> {
    return this.httpClient.get<ParentTask[]>(this.endpoint + 'findAllParentTasks', this.httpOptions);
  }

  getParentById(parentId) {
    return this.httpClient.get<any>(this.endpoint + 'getParentTask?parentId=' + parentId, this.httpOptions);
  }

  addTask(task: Task) {

    console.log(task);
    return this.httpClient.post<any>(this.endpoint + 'insertTask', JSON.stringify(task), this.httpOptions);

  }

  update(task: Task, taskId) {

    console.log(task);
    return this.httpClient.post<any>(this.endpoint + 'updateTask?taskId=' + taskId, JSON.stringify(task), this.httpOptions);

  }


  getTasksForProjectId(projectId) {

    return this.httpClient.get<Task[]>(this.endpoint + 'findTasksForProjectId?projectId=' + projectId,
      this.httpOptions);

  }

  endTaskForTaskId(taskId) {
    console.log(taskId)
    return this.httpClient.post<any>(this.endpoint + 'endTask?taskId=' + taskId , this.httpOptions);
  }

   getTaskById(taskId) {
     return this.httpClient.get<any>(this.endpoint + 'getTaskById?taskId=' + taskId , this.httpOptions);
   }
}
