var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];

var state = new State(3);
state.updateEmployees(1, 3);

span.onclick = function() {
  modal.style.display = "none";
}

window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}







