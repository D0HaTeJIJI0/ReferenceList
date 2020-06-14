class TableBuilder {

  static initializeTableHeader(state) {
    let ths = document.getElementsByTagName('th');
    let employees = state.pageEmployee.employees;

    for (let th of ths) {
      th.onclick = () => {
        let column = th.dataset["column"];
        let order = th.dataset["order"];
        let text = th.innerHTML;
        text = text.substring(0, text.length - 1);

        if (order == 'desc') {
          th.dataset["order"] = 'asc';
          employees = employees.sort((a, b) => a[column] == b[column] ? 0 :
                                                              a[column] < b[column] ? 1 :
                                                                                      -1);
          text += "&#9650";
          this.buildTable(employees);
        } else {
          th.dataset["order"] = 'desc';
          employees = employees.sort((a, b) => a[column] == b[column] ? 0 :
                                                              a[column] > b[column] ? 1 :
                                                                                      -1);
          text += "&#9660";
          this.buildTable(employees);
        }
        th.innerHTML = text;
      }
    }
  }

  static buildTable(employees) {
    let employeeRows = "";
  
    employees.forEach(employee => {
      employeeRows += `<tr class="employeeRow">`;
      employeeRows += "<td>" + employee.id + "</td>";
      employeeRows += "<td>" + employee.name + "</td>";
      employeeRows += "<td>" + employee.age + "</td>";
      employeeRows += "<td>" + employee.married + "</td>";
      employeeRows += "<td>" + employee.bossId + "</td>";
      employeeRows += "</tr>";
    });
    
    document.getElementById("employees").innerHTML = employeeRows;
  
    employeeRows = document.getElementsByClassName('employeeRow');
    for (let employeeRow of employeeRows) {
      employeeRow.onclick = function() {
        modal.style.display = "block";
      }
    }
  }
}