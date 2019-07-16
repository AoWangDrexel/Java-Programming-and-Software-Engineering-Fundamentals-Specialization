function clickMe() {
  alert("You clicked it!");
}

function confirmIt() {
  var choice = confirm("Okay or cancel?");
  if (choice) {
    alert("You clicked okay!");
  } else if (!choice) {
    alert("You clicked cancel!");
  }
}
