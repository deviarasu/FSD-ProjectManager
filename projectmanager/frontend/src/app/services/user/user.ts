export class User {

  readonly firstName: string;
  readonly lastName: string;
  readonly employeeId: string;
  userId: string

  constructor(firstName: string, lastName: string, employeeId: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.employeeId = employeeId;
  }

  public getFirstName(): string {
    return this.firstName;
  }

  public getLastName(): string {
    return this.lastName;
  }

  public getEmployeeId(): string {
    return this.employeeId;
  }

  public  getUserId(): string {
    return this.userId;
  }

  public setUserId(userId: string) {
    this.userId = userId;
  }

}
