class TableBuilder {

  static initializeTableHeader(state) {
    let ths = document.getElementsByClassName('data-header');
    let employees = state.pageEmployee.employeeDtos;

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
  
    for (let i = 0; i < employees.length; i++) {
      employeeRows += `<tr id=${i} class="employeeRow">`;
      employeeRows += `<td><input type="checkbox" class="checkitem" onchange="checkItem()" id="${employees[i].id}" /></td>`;
      employeeRows += "<td>" + employees[i].name + "</td>";
      employeeRows += "<td>" + employees[i].age + "</td>";
      employeeRows += "<td>" + employees[i].married + "</td>";
      employeeRows += "</tr>";
    }
    
    document.getElementById("employees").innerHTML = employeeRows;
  
    employeeRows = document.getElementsByClassName('employeeRow');
    for (let employeeRow of employeeRows) {
      let dataCells = employeeRow.getElementsByTagName('td');
      for (let i = 1; i < dataCells.length; i++) {
        dataCells[i].onclick = function() {
          employeeInfoModal.style.display = "block";
          let employeeId = employeeRow.id;
          let employeeInfo = employees[employeeId];
          let employeeInfoHTML = "<p> <b>id</b>: " + employeeInfo.id + "</p>"
          employeeInfoHTML += "<p> <b>name</b>: " + employeeInfo.name + "</p>"
          employeeInfoHTML += "<p> <b>age</b>: " + employeeInfo.age + "</p>"
          employeeInfoHTML += "<p> <b>married</b>: " + employeeInfo.married + "</p>"
          employeeInfoHTML += "<p> <b>bossId</b>: " + employeeInfo.bossId + "</p>"
          employeeInfoHTML += "<p> <b>subordinatorIds</b>:</p>"
          employeeInfo.subordinateIds.forEach(subordinator => {
            employeeInfoHTML += "<p>" + subordinator + "</p>"
          })
  
          document.getElementById('employeeInfo').innerHTML = employeeInfoHTML;
        }
      }
    }
  }
}