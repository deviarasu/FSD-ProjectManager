import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Project} from "./project";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  endpoint = 'http://localhost:8736/api/pm/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };


  constructor(private  httpClient:  HttpClient) { }

  addProject(project: Project) {

    return this.httpClient.post<any>(this.endpoint + 'insertProject', JSON.stringify(project), this.httpOptions);
  }

  getAllProjects() : Observable<Project[]> {

    return this.httpClient.get<Project[]>(this.endpoint + 'findAllProjects', this.httpOptions);
  }

  deleteProject(projectId: string) {

    let response =  this.httpClient.request('delete',
      this.endpoint + 'deleteProject?projectId=' + projectId);
    return response;
  }

  updateProject(project: Project, projectId: String) {

    return this.httpClient.post<any>(this.endpoint + 'updateProject?projectId=' + projectId,
      JSON.stringify(project), this.httpOptions);
  }
}
