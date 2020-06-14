class EmployeeApi {

  static async fetchEmployees(page, pageSize) {
    const response = await fetch(`http://localhost:8081/referenceList/employee/all?page=${page - 1}&size=${pageSize}`);
    if (response.ok) {
      const pageEmployeeJson = await response.json();
  
      if (pageEmployeeJson) {
        return new PageEmployeeDto(pageEmployeeJson.employeeDtos,
            pageEmployeeJson.currentPage + 1,
            pageSize,
            pageEmployeeJson.totalPageNumber
          );
      }
    }
  }

}