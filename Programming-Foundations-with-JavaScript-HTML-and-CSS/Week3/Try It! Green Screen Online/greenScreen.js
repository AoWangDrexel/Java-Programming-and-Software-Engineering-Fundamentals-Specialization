var fgImg = null;
var bgImg = null;
var fgCanvas = document.getElementById("fgcan");
var bgCanvas = document.getElementById("bgcan");

function loadForegroundImage() {
  var fgInput = document.getElementById("fgbtn");
  fgImg = new SimpleImage(fgInput);
  fgImg.drawTo(fgCanvas);
}

function loadBackgroundImage() {
  var bgInput = document.getElementById("bgbtn");
  bgImg = new SimpleImage(bgInput);
  bgImg.drawTo(bgCanvas);
}

function doGreenScreen() {
  if (fgImg == null || !fgImg.complete()) {
    alert("The foreground image has not been loaded!");
  }
  if (bgImg == null || !bgImg.complete()) {
    alert("The backgroundground image has not been loaded!");
  }
  var combo = new SimpleImage(fgImg.getWidth(), fgImg.getHeight());
  bgImg.setSize(fgImg.getWidth(), fgImg.getHeight());
  for (var px of fgImg.values()) {
    var x = px.getX();
    var y = px.getY();
    if (px.getGreen() > px.getRed() + px.getBlue()) {
      var bgPx = bgImg.getPixel(x, y);
      combo.setPixel(x, y, bgPx);
    } else {
      combo.setPixel(x, y, px);
    }
  }
  checkSize();
  var bgCxt = bgCanvas.getContext("2d");
  bgCxt.clearRect(0, 0, bgCanvas.width, bgCanvas.height);
  combo.drawTo(fgCanvas);
}

function clearCanvas() {
  var fgCxt = fgCanvas.getContext("2d");
  var bgCxt = bgCanvas.getContext("2d");
  fgCxt.clearRect(0, 0, fgCanvas.width, fgCanvas.height);
  bgCxt.clearRect(0, 0, bgCanvas.width, bgCanvas.height);
}

function checkSize() {
  if (fgCanvas.width != bgCanvas.width || fgCanvas.height != bgCanvas.height) {
    alert("Foreground size does not match the background size");
    alert("No worries, I'll fix it!");
  }
}
