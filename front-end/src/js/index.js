var state = {
  'employees': [],
  'page': 1,
  'pageSize': 3,
  'totalPageNumber': 0,
  'windowSize': 3
}

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
  let minPage = Math.max(state.page - Math.floor((state.windowSize - 1) / 2), 1);
  let maxPage = minPage + state.windowSize - 1;
  if (maxPage > state.totalPageNumber) {
    maxPage = state.totalPageNumber;
    minPage = Math.max(maxPage - state.windowSize + 1, 1)
  }

  if (minPage > 1) {
    buttons += `<button value=1 class="page btn btn-sm btn-info">&#171; First</button>`;
  }
  if (state.page > 1) {
    buttons += `<button class="page-backward btn btn-sm btn-info">&#139;</button>`;
  }
  for (let page = minPage; page <= maxPage; page++) {
    if (page == state.page) {
      buttons += `<button value=${page} class="page btn btn-sm btn-info active">${page}</button>`;  
    } else {
      buttons += `<button value=${page} class="page btn btn-sm btn-info">${page}</button>`;
    }
  }
  if (state.page < state.totalPageNumber) {
    buttons += `<button class="page-forward btn btn-sm btn-info">&#155;</button>`;
  }
  if (maxPage < state.totalPageNumber) {
    buttons += `<button value=${state.totalPageNumber} class="page btn btn-sm btn-info">&#187; Last</button>`;
  }

  wrapper.innerHTML = buttons;

  let backwards = document.getElementsByClassName("page-backward");
  for (let backward of backwards) {
    backward.onclick = () => {
      state.page--;
      fetchEmployees();
    }
  }

  let forwards = document.getElementsByClassName("page-forward");
  for (let forward of forwards) {
    forward.onclick = () => {
      state.page++;
      fetchEmployees();
    }
  }

  let pages = document.getElementsByClassName("page");
  for (let page of pages) {
    page.onclick = () => {
      state.page = Number(page.value);
      fetchEmployees();
    }
  }
}

function changePageSize(pageSize) {
  state.pageSize = pageSize.value;
  fetchEmployees();
}