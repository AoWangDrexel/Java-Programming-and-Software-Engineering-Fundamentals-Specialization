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

function changeColor() {
  var bIColor = document.getElementById("intro");
  bIColor.className = "backIntro";
  var bBColor = document.getElementById("body");
  bBColor.className = "backBody";
}

function changeText() {
  var bIText = document.getElementById("intro");
  var bBText = document.getElementById("body");
  bIText.innerHTML =
    "Benvenuto nella mia pagina! Avremo a che fare con i pulsanti oggi";
  bBText.innerHTML = "Welp! Veramente divertente testare con JS";
}

function changeColorText() {
  var textIColor = document.getElementById("intro");
  var textBColor = document.getElementById("body");
  textIColor.style.color = "#546e7a";
  textBColor.style.color = "#f48fb1";
}

function changeValue() {
  document.getElementById("btnIntro").value = "bam! new click me!";
  document.getElementById("btnBody").value = "bam! new confirm?!";
}
