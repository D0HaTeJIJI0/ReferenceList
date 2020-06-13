async function fetchEmployees() {
  const response = await fetch('http://localhost:8081/referenceList/employee/all');
  if (response.ok) {
    const pageEmployeeJson = await response.json();

    if (pageEmployeeJson) {
      let employeeJson = pageEmployeeJson.employeeDtos;
      let employeeRows = "";

      employeeJson.forEach(employee => {
        employeeRows += "<tr>";
        employeeRows += "<td>" + employee.id + "</td>";
        employeeRows += "<td>" + employee.name + "</td>";
        employeeRows += "<td>" + employee.age + "</td>";
        employeeRows += "<td>" + employee.married + "</td>";
        employeeRows += "<td>" + employee.bossId + "</td>";
        employeeRows += "</tr>";
      });

      document.createElement
      document.getElementById("employees").innerHTML = employeeRows;
    }
  }
}

fetchEmployees();