class State {

  constructor(windowSize) {
    this.windowSize = windowSize;
  }

  async updateEmployees(page, pageSize) {
    this.pageEmployee = await EmployeeApi.fetchEmployees(page, pageSize);

    TableBuilder.initializeTableHeader(this);
    TableBuilder.buildTable(this.pageEmployee.employees);
    PaginationBuilder.buildPagination(this);
  }

  updatePageSize(pageSize) {
    this.updateEmployees(this.pageEmployee.page, pageSize);
  }

  goToPage(page) {
    this.updateEmployees(page, this.pageEmployee.pageSize);
  }

  goToNextPage() {
    this.updateEmployees(this.pageEmployee.page + 1, this.pageEmployee.pageSize);
  }

  goToPreviousPage() {
    this.updateEmployees(this.pageEmployee.page - 1, this.pageEmployee.pageSize);
  }
}