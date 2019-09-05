import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectComponent } from './project/project.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { ViewTaskComponent } from './view-task/view-task.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  { path: 'addProject', component: ProjectComponent },
  { path: 'addTask', component: AddTaskComponent },
  { path: 'addTask/:type', component: AddTaskComponent },
  { path: 'viewTask', component: ViewTaskComponent },
  { path: 'addUser', component: UserComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
