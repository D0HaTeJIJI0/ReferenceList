class State {

  constructor(windowSize) {
    this.windowSize = windowSize;
  }

  async updateEmployees(page, pageSize) {
    this.pageEmployee = await EmployeeApi.fetchEmployees(page, pageSize);

    TableBuilder.initializeTableHeader(this);
    TableBuilder.buildTable(this.pageEmployee.employeeDtos);
    PaginationBuilder.buildPagination(this);
  }

  updatePageSize(pageSize) {
    this.updateEmployees(this.pageEmployee.currentPage, pageSize);
  }

  goToPage(page) {
    this.updateEmployees(page, this.pageEmployee.currentPageSize);
  }

  goToNextPage() {
    this.updateEmployees(this.pageEmployee.currentPage + 1, this.pageEmployee.currentPageSize);
  }

  goToPreviousPage() {
    this.updateEmployees(this.pageEmployee.currentPage - 1, this.pageEmployee.currentPageSize);
  }

  async deleteSelectedEmployees() {
    let checkitems = document.getElementsByClassName("checkitem");
    let ids = [];
    for (let checkitem of checkitems) {
      if (checkitem.checked) {
        ids.push({"id": checkitem.id});
      }
    }
    await EmployeeApi.deleteEmployees(ids);

    this.updateEmployees(this.pageEmployee.page, this.pageEmployee.pageSize);
  }

  addEmployee() {
   
  }
}