class State {

  constructor(windowSize) {
    this.windowSize = windowSize;
  }

  async updateEmployees() {
    if (this.pageEmployee) {
      this.pageEmployee = await EmployeeApi.fetchEmployees(this.pageEmployee.currentPage,
        this.pageEmployee.pageSize);
    }
    else {
      this.pageEmployee = await EmployeeApi.fetchEmployees();
    }

    TableBuilder.initializeTableHeader(this);
    TableBuilder.buildTable(this.pageEmployee.employeeDtos);
    PaginationBuilder.buildPagination(this);
  }

  updatePageSize(pageSize) {
    this.pageEmployee.currentPage = 1;
    this.pageEmployee.pageSize = pageSize;
    this.updateEmployees();
  }

  goToPage(page) {
    this.pageEmployee.currentPage = page;
    this.updateEmployees();
  }

  goToNextPage() {
    this.pageEmployee.currentPage++;
    this.updateEmployees();
  }

  goToPreviousPage() {
    this.pageEmployee.currentPage--;
    this.updateEmployees();
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

    this.pageEmployee.currentPage = 1;
    await this.updateEmployees();
  }

  async addEmployee(form) {
    let elements = form.elements;
    let employee = {
      "name": elements[0].value,
      "age": elements[1].value,
      "married": elements[2].value == "yes"
    };
    await EmployeeApi.addEmployee(employee);

    this.pageEmployee.currentPage = 1;
    console.log(this.pageEmployee)
    await this.updateEmployees();
  }
}