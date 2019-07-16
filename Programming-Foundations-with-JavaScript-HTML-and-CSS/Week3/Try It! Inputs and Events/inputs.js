function changeColor() {
  document.getElementById("can").style.backgroundColor = "#9575cd";
}

function changeDiffColor() {
  var canColor = document.getElementById("can");

  var clrInput = document.getElementById("clr");

  var color = clrInput.value;
  canColor.style.backgroundColor = color;
}

function makeSqr() {
  var can = document.getElementById("can");
  var sldr = document.getElementById("sldr");
  
  var input = sldr.value;
  var ctx = can.getContext("2d");
  
  ctx.clearRect(0, 0, can.width, can.height);
  ctx.fillStyle = "black";
  
  ctx.fillRect(10, 10, input, input);
  ctx.fillRect(parseInt(input) + 20, 10, input, input);
  ctx.fillRect(parseInt(input) * 4, 10, parseInt(input) * 4, input);
}

