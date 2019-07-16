var image;

function upload() {
  var canvas1 = document.getElementById("can1");
  var canvas2 = document.getElementById("can2");
  var file = document.getElementById("fInput");
  image = new SimpleImage(file);
  image.drawTo(canvas1);
  image.drawTo(canvas2);
}

function makeGrey() {
  var canvas = document.getElementById("can2");
  for (var px of image.values()) {
    var red = px.getRed();
    var green = px.getGreen();
    var blue = px.getBlue();
    var avg = (red + green + blue) / 3;
    px.setRed(avg);
    px.setGreen(avg);
    px.setBlue(avg);
  }

  image.drawTo(canvas);
}
