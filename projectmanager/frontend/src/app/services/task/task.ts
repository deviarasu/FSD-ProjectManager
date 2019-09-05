export class Task {

  readonly projectId;
  readonly parentId;
  readonly taskName: string;
  readonly startDate: string;
  readonly endDate: string;
  readonly priority;
  status;
  taskId: string

  constructor(projectId, parentId, taskName, startDate, endDate, priority) {
    this.projectId = projectId;
    this.parentId = parentId;
    this.taskName = taskName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priority = priority;
  }

  public setTaskId(taskId: string) {
    this.taskId = taskId;
  }

  public setStatus(status: string) {
    this.status = status;
  }

}
