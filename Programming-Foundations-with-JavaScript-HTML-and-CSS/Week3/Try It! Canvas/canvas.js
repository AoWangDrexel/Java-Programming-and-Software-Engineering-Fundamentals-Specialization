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

function doPink() {
  var c1Color = document.getElementById("can1");
  c1Color.style.backgroundColor = "#f48fb1";
  var context = c1Color.getContext("2d");

  context.fillStyle = "#bc477b";
  context.fillRect(10, 10, 100, 100);
  context.fillRect(150, 10, 100, 100);

  context.fillStyle = "black";
  context.font = "30px Arial";
  context.fillText("Hello World", 10, 50);
}

function doBlack() {
  var c2Color = document.getElementById("can2");
  c2Color.style.backgroundColor = "#9e9e9e";

  //clear the screen
  document
    .getElementById("can1")
    .getContext("2d")
    .clearRect(0, 0, c2Color.width, c2Color.height);
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

function changeBorder() {
  document.getElementById("intro").style.borderColor = "#546e7a";
  document.getElementById("body").style.borderColor = "#f48fb1";
}

function changeValue() {
  document.getElementById("btnIntro").value = "bam! new click me!";
  document.getElementById("btnBody").value = "bam! new confirm?!";
}

