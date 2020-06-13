var currentEmployees = [];

fetchEmployees();

let ths = document.getElementsByTagName('th');

for (let th of ths) {
  th.onclick = () => {
    let column = th.dataset["column"];
    let order = th.dataset["order"];
    let text = th.innerHTML;
    text = text.substring(0, text.length - 1);

    if (order == 'desc') {
      th.dataset["order"] = 'asc';
      currentEmployees = currentEmployees.sort((a, b) => a[column] == b[column] ? 0 :
                                                          a[column] < b[column] ? 1 :
                                                                                  -1);
      text += "&#9650";
      buildTable(currentEmployees)
    } else {
      th.dataset["order"] = 'desc';
      currentEmployees = currentEmployees.sort((a, b) => a[column] == b[column] ? 0 :
                                                          a[column] > b[column] ? 1 :
                                                                                  -1);
      text += "&#9660";
      buildTable(currentEmployees)
    }
    th.innerHTML = text;
  }
}

async function fetchEmployees() {
  const response = await fetch('http://localhost:8081/referenceList/employee/all');
  if (response.ok) {
    const pageEmployeeJson = await response.json();

    if (pageEmployeeJson) {
      currentEmployees = pageEmployeeJson.employeeDtos;

      buildTable(currentEmployees)
    }
  }
}

function buildTable(employees) {
  let employeeRows = "";

  employees.forEach(employee => {
    employeeRows += "<tr>";
    employeeRows += "<td>" + employee.id + "</td>";
    employeeRows += "<td>" + employee.name + "</td>";
    employeeRows += "<td>" + employee.age + "</td>";
    employeeRows += "<td>" + employee.married + "</td>";
    employeeRows += "<td>" + employee.bossId + "</td>";
    employeeRows += "</tr>";
  });
  
  document.getElementById("employees").innerHTML = employeeRows;
}