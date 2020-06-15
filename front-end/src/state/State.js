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

  async addEmployee(form) {
    let elements = form.elements;
    let employee = {
      "name": elements[0].value,
      "age": elements[1].value,
      "married": elements[2].value == "yes"
    };
   await EmployeeApi.addEmployee(employee);
   await this.updateEmployees(this.pageEmployee.page, this.pageEmployee.pageSize);
  }
}