var img = null;
var redImg = null;
var greyImg = null;
var rainbowImg = null;
var anyColorImg = null;
var blurImg = null;
var canvas = document.getElementById("can");

function loadImage() {
  var fileInput = document.getElementById("finput");
  img = new SimpleImage(fileInput);
  redImg = new SimpleImage(fileInput);
  greyImg = new SimpleImage(fileInput);
  rainbowImg = new SimpleImage(fileInput);
  anyColorImg = new SimpleImage(fileInput);
  blurImg = new SimpleImage(fileInput);
  img.drawTo(canvas);
}
function setAll(px, r, g, b) {
  px.setRed(r);
  px.setGreen(g);
  px.setBlue(b);
}
function filterGrey() {
  for (var px of greyImg.values()) {
    var red = px.getRed();
    var green = px.getGreen();
    var blue = px.getBlue();
    var avg = (red + green + blue) / 3;
    setAll(px, avg, avg, avg);
  }
}

function doGrey() {
  if (imageIsLoaded(greyImg)) {
    filterGrey();
    greyImg.drawTo(canvas);
  }
}

function filterRed() {
  for (var px of redImg.values()) {
    var red = px.getRed();
    var green = px.getGreen();
    var blue = px.getBlue();
    var avg = (red + green + blue) / 3;
    if (avg < 128) {
      setAll(px, 2 * avg, 0, 0);
    } else {
      setAll(px, 255, 2 * avg - 255, 2 * avg - 255);
    }
  }
}

function doRed() {
  if (imageIsLoaded(redImg)) {
    filterRed();
    redImg.drawTo(canvas);
  }
}

function filterRainbow() {
  for (var px of rainbowImg.values()) {
    var y = px.getY();
    var height = rainbowImg.getHeight();
    var avg = (px.getRed() + px.getBlue() + px.getGreen()) / 3;
    if (y < height / 7) {
      // red
      if (avg < 128) {
        setAll(px, 2 * avg, 0, 0);
      } else {
        setAll(px, 255, 2 * avg - 255, 2 * avg - 255);
      }
      // orange
    } else if (y > height / 7 && y < 2 * height / 7) {
      if (avg < 128) {
        setAll(px, 2 * avg, 0.8 * avg, 0);
      } else {
        setAll(px, 255, 1.2 * avg - 51, 2 * avg - 255);
      }
      // yellow
    } else if (y > 2 * height / 7 && y < 3 * height / 7) {
      if (avg < 128) {
        setAll(px, 2 * avg, 2 * avg, 0);
      } else {
        setAll(px, 255, 255, 2 * avg - 255);
      }
      // blue
    } else if (y > 3 * height / 7 && y < 4 * height / 7) {
      if (avg < 128) {
        setAll(px, 0, 2 * avg, 0);
      } else {
        setAll(px, 2 * avg - 255, 255, 2 * avg - 255);
      }
      // green
    } else if (y > 4 * height / 7 && y < 5 * height / 7) {
      if (avg < 128) {
        setAll(px, 0, 0, 2 * avg);
      } else {
        setAll(px, 2 * avg - 255, 2 * avg - 255, 255);
      }
      // indigo
    } else if (y > 5 * height / 7 && y < 6 * height / 7) {
      if (avg < 128) {
        setAll(px, 0.8 * avg, 0, 2 * avg);
      } else {
        setAll(px, 1.2 * avg - 51, 2 * avg - 255, 255);
      }
      // violet
    } else {
      if (avg < 128) {
        setAll(px, 1.6 * avg, 0, 1.6 * avg);
      } else {
        setAll(px, 0.4 * avg + 153, 2 * avg - 255, 0.4 * avg + 153);
      }
    }
  }
}
function doRainbow() {
  if (imageIsLoaded(rainbowImg)) {
    filterRainbow();
    rainbowImg.drawTo(canvas);
  }
}
function filterBlur() {
  for (var px of blurImg.values()) {
    var x = px.getX();
    var y = px.getY();
    if (Math.random() < 0.5) {
      //change pixel
      var newPx = getNearbyPixel(x, y);
      blurImg.setPixel(x, y, newPx);
    }
  }
}
function doBlur() {
  if (imageIsLoaded(blurImg)) {
    filterBlur();
    blurImg.drawTo(canvas);
  }
}
function getNearbyPixel(x, y) {
  //returns a nearby pixel
  var radius = 10;
  var dx = Math.random() * 2 * radius - radius;
  var dy = Math.random() * 2 * radius - radius;
  var newX = ensureInImage(x + dx, blurImg.getWidth());
  var newY = ensureInImage(y + dx, blurImg.getHeight());
  var newPix = blurImg.getPixel(newX, newY);
  return newPix;
}

function ensureInImage(coord, size) {
  //returns acceptable coordinate
  if (coord < 0) {
    return 0;
  }
  if (coord >= size) {
    return size - 1;
  } else {
    return coord;
  }
}

function hexToDecimal(hex) {
  var total = 0;
  for (var i = 0; i < 2; i++) {
    var isnum = /^\d+$/.test(hex.charAt(i));
    if (isnum) {
      if (i == 1) {
        total += parseInt(hex.charAt(i));
      } else {
        total += 16 * parseInt(hex.charAt(i));
      }
    } else {
      if (i == 1) {
        total += alphaLibrary(hex.charAt(i));
      } else {
        total += 16 * alphaLibrary(hex.charAt(i));
      }
    }
  }
  return total;
}

function alphaLibrary(letter) {
  if (letter == "a") {
    return 10;
  } else if (letter == "b") {
    return 11;
  } else if (letter == "c") {
    return 12;
  } else if (letter == "d") {
    return 13;
  } else if (letter == "e") {
    return 14;
  } else if (letter == "f") {
    return 15;
  } else {
    return 0;
    alert("Error: not part of hexdecimal");
  }
}
function filterAnyColor() {
  var cInput = document.getElementById("anycolor");
  var color = cInput.value;
  var red = color.substring(1, 3);
  var green = color.substring(3, 5);
  var blue = color.substring(5, 7);
  var redDec = hexToDecimal(red);
  var greenDec = hexToDecimal(green);
  var blueDec = hexToDecimal(blue);

  // R	= Rc/127.5*avg, for avg < 128
  // (2 - Rc/127.5)*avg + 2*Rc - 255, for avg >=128

  for (var px of anyColorImg.values()) {
    var avg = (px.getRed() + px.getGreen() + px.getBlue()) / 3;
    if (avg < 128) {
      setAll(
        px,
        redDec / 127.5 * avg,
        greenDec / 127.5 * avg,
        blueDec / 127.5 * avg
      );
    } else {
      setAll(
        px,
        (2 - redDec / 127.5) * avg + 2 * redDec - 255,
        (2 - greenDec / 127.5) * avg + 2 * greenDec - 255,
        (2 - blueDec / 127.5) * avg + 2 * blueDec - 255
      );
    }
  }
}
function doAnyColor() {
  if (imageIsLoaded(anyColorImg)) {
    filterAnyColor();
    anyColorImg.drawTo(canvas);
  }
}

function reset() {
  if (imageIsLoaded(img)) {
    img.drawTo(canvas);
    redImg = new SimpleImage(img);
    greyImg = new SimpleImage(img);
    rainbowImg = new SimpleImage(img);
    anyColorImg = new SimpleImage(img);
    blurImg = new SimpleImage(img);
  }
}

function erase() {
  var cxt = canvas.getContext("2d");
  cxt.clearRect(0, 0, canvas.width, canvas.height);
  var fileInput = document.getElementById("finput");
}

function imageIsLoaded(img) {
  if (img == null || !img.complete()) {
    alert("Image has not loaded yet");
    return false;
  } else {
    return true;
  }
}
