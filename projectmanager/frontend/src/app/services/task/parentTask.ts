export class ParentTask {

  readonly parentTask: string;
  parentId: string

  constructor(parentTask: string) {
    this.parentTask = parentTask;
  }

  public getParentTask(): string {
    return this.parentTask;
  }

  public  getParenId(): string {
    return this.parentId;
  }

  public setParentId(parentId: string) {
    this.parentId = parentId;
  }

}
