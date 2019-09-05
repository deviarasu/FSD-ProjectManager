import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, of } from 'rxjs';
import {User} from "./user";
import {stringify} from "querystring";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  endpoint = 'http://localhost:8736/api/pm/';

  httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

  constructor(private  httpClient:  HttpClient) { }

  addUser(user: User) {

    return this.httpClient.post<any>(this.endpoint + 'insertUser', JSON.stringify(user), this.httpOptions);
  }

  getAllUsers() : Observable<User[]> {

    return this.httpClient.get<User[]>(this.endpoint + 'findAllUsers', this.httpOptions);
  }

  deleteUser(userId: String) {

   let response =  this.httpClient.request('delete',
     this.endpoint + 'deleteUser?userId=' + userId);


   console.log(response);
   return response;
  }

  updateUser(user: User, userId: String) {

    return this.httpClient.post<any>(this.endpoint + 'updateUser?userId=' + userId, JSON.stringify(user), this.httpOptions);
  }
}
