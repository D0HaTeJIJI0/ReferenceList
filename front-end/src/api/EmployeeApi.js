class EmployeeApi {

  static async fetchEmployees(page, pageSize) {
    const response = await fetch(`http://localhost:8081/referenceList/employee/all?page=${page - 1}&size=${pageSize}`);
    if (response.ok) {
      const pageEmployeeJson = await response.json();
  
      if (pageEmployeeJson) {
        pageEmployeeJson.currentPage++;
        pageEmployeeJson.currentPageSize = pageSize;
        return pageEmployeeJson;
      }
    }
  }

  static async fetchEmployeeInfo(id) {
    const response = await fetch(`http://localhost:8081/referenceList/employee/${id}`);
    if (response.ok) {
      const employeeInfoJson = await response.json();
  
      if (employeeInfoJson) {
        return employeeInfoJson
      }
    }
  }

  static async deleteEmployees(ids) {
    await fetch(`http://localhost:8081/referenceList/employee/delete`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json;charset=utf-8'
      },
      body: JSON.stringify(ids)
    });
  }

  static async addEmployee(employee) {
    await fetch(`http://localhost:8081/referenceList/employee/`)
  }

}