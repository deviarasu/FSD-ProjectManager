import { Component, OnInit } from '@angular/core';
import { User} from "../services/user/user";
import {UserService} from "../services/user/user.service";
import { OrderPipe } from 'ngx-order-pipe';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  firstName;
  lastName;
  employeeId;
  userId;
  userList: Array<Object> = [];
  isEdit = false;

  constructor(private userService: UserService,
              private orderPipe: OrderPipe) { }

  ngOnInit() {
    this.getAllUsers();
    this.isEdit = false;
  }

  saveUser(form) {

    let user = new User(this.firstName, this.lastName, this.employeeId);

    if(this.userId > 0) {
      this.userService.updateUser(user, this.userId).subscribe(
        (data) => { console.log("Successfully updated User");
          form.resetForm();
          this.isEdit = false;
          this.getAllUsers();
        },
        error => {console.error("Error while adding user")}

      );

    } else {

      this.userService.addUser(user).subscribe(
        (data) => {
          console.log("Successfully added User");
          form.resetForm();
          this.getAllUsers();
        },
        error => {
          console.error("Error while adding user")
        }
      );
    }

  }

  getAllUsers() {

    this.userService.getAllUsers().subscribe(val => {this.userList = val;});
  }

  deleteUser(user) {

    this.userService.deleteUser(user.userId).subscribe(
    (data) => { console.log("Successfully deleted User");
        this.getAllUsers(); }

  );
}

  updateScreenChange(user) {

    if(this.isEdit) {
      this.isEdit = false;
      this.firstName = this.lastName = this.employeeId = this.userId = '';
    } else {
      this.firstName = user.firstName;
      this.lastName = user.lastName;
      this.employeeId = user.employeeId;
      this.userId = user.userId;
      this.isEdit = true;
    }

  }

  sortBy(type: string) {
    if(type === 'first') {
      this.userList = this.orderPipe.transform(this.userList, 'firstName');
    } else if(type === 'last') {
      this.userList = this.orderPipe.transform(this.userList, 'lastName');
    } else if(type === 'id') {
      this.userList = this.orderPipe.transform(this.userList, 'employeeId');
    }
  }

}
