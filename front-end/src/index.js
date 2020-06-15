var employeeInfoModal = document.getElementById("employeeInfoModal");
var addEmployeeModal = document.getElementById("addEmployeeModal");

var state = new State(3);
state.updateEmployees();

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

function checkAll(checkAll) {
  let checkitems = document.getElementsByClassName("checkitem");
  for (let checkitem of checkitems) {
    checkitem.checked = checkAll.checked;
  }
}

function checkItem() {
  document.getElementById("checkall").checked = false;
}

async function addEmployee(form) {
  await state.addEmployee(form);
  addEmployeeModal.style.display = "none";

  return false;
}







