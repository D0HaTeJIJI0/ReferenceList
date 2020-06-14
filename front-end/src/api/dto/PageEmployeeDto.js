class PageEmployeeDto {

  constructor(employees = [], page = 1, pageSize = 0, totalPageNumber = 0) {
    this.employees = employees;
    this.page = page;
    this.pageSize = pageSize;
    this.totalPageNumber = totalPageNumber;
  }

  // set setEmployees(value) {
  //   this.employees = value;
  // }

  // set setPage(value) {
  //   this.page = value;
  // }

  // set setPageSize(value) {
  //   this.pageSize = value;
  // }

  // set setTotalPageNumber(value) {
  //   this.totalPageNumber = value;
  // }

  // get getEmployees() {
  //   return this.employees;
  // }

  // get getPage() {
  //   return this.page;
  // }

  // get getPageSize() {
  //   return this.pageSize;
  // }

  // get getTotalPageNumber() {
  //   return this.totalPageNumber;
  // }

}