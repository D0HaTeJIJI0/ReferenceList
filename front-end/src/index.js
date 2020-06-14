var employeeInfoModal = document.getElementById("employeeInfoModal");
var addEmployeeModal = document.getElementById("addEmployeeModal");

var state = new State(3);
state.updateEmployees(1, 3);

employeeInfoModal.getElementsByClassName("close")[0].onclick = function() {
  employeeInfoModal.style.display = "none";
}

addEmployeeModal.getElementsByClassName("close")[0].onclick = function() {
  addEmployeeModal.style.display = "none";
}

window.onclick = function(event) {
  if (event.target == employeeInfoModal) {
    employeeInfoModal.style.display = "none";
  } 
  else if (event.target == addEmployeeModal) {
    addEmployeeModal.style.display = "none";
  }
}

function showAddEmployeeModal() {
  addEmployeeModal.style.display = "block";
}

function checkAll() {
  let checkitems = document.getElementsByClassName("checkitem");
  for (let checkitem of checkitems) {
    checkitem.checked = !checkitem.checked;
  }
}







