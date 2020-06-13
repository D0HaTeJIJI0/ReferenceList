var state = {
  'employees': [],
  'page': 1,
  'pageSize': 3,
  'totalPageNumber': 0
}

fetchEmployees(state.page, state.pageSize);

let ths = document.getElementsByTagName('th');

for (let th of ths) {
  th.onclick = () => {
    let column = th.dataset["column"];
    let order = th.dataset["order"];
    let text = th.innerHTML;
    text = text.substring(0, text.length - 1);

    if (order == 'desc') {
      th.dataset["order"] = 'asc';
      state.employees = state.employees.sort((a, b) => a[column] == b[column] ? 0 :
                                                          a[column] < b[column] ? 1 :
                                                                                  -1);
      text += "&#9650";
      buildTable();
    } else {
      th.dataset["order"] = 'desc';
      state.employees = state.employees.sort((a, b) => a[column] == b[column] ? 0 :
                                                          a[column] > b[column] ? 1 :
                                                                                  -1);
      text += "&#9660";
      buildTable();
    }
    th.innerHTML = text;
  }
}

async function fetchEmployees() {
  const response = await fetch(`http://localhost:8081/referenceList/employee/all?page=${state.page-1}&size=${state.pageSize}`);
  if (response.ok) {
    const pageEmployeeJson = await response.json();

    if (pageEmployeeJson) {
      state.employees = pageEmployeeJson.employeeDtos;
      state.totalPageNumber = pageEmployeeJson.totalPageNumber;

      buildTable();
      buildPagination();
    }
  }
}

function buildTable() {
  let employeeRows = "";

  state.employees.forEach(employee => {
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

function buildPagination() {
  let wrapper = document.getElementById('pagination-wrapper');
  let buttons = "";

  for (let page = 1; page <= state.totalPageNumber; page++) {
    buttons += `<button value=${page} class="page btn btn-sm btn-info">${page}</button>`;
  }

  wrapper.innerHTML = buttons;

  let pages = document.getElementsByClassName("page");
  for (let page of pages) {
    page.onclick = () => {
      state.page = page.value;
      fetchEmployees();
      buildTable();
    }
  }
}