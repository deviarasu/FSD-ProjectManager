export class Project {

  readonly projectName: string;
  readonly managerName: string;
  readonly priority;
  readonly startDate: string;
  readonly endDate: string;
  projectId: string

  constructor(projectName: string, managerName: string, priority, startDate: string, endDate: string) {
    this.projectName = projectName;
    this.managerName = managerName;
    this.priority = priority;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public getProjectName(): string {
    return this.projectName;
  }

  public getManagerName(): string {
    return this.managerName;
  }

  public getPriority() {
    return this.priority;
  }

  public getStartDate() : string {
    return this.startDate;
  }

  public gtEndDate() : string {
    return this.endDate;
  }

  public  getProjectId(): string {
    return this.projectId;
  }

  public setProjectId(projectId: string) {
    this.projectId = projectId;
  }

}
