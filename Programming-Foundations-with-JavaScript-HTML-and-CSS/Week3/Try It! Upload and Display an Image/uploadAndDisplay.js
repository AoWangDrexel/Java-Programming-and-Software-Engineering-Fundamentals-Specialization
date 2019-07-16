function upload() {
  var can = document.getElementById("can");
  var file = document.getElementById("fInput");
  var image = new SimpleImage(file);
  image.drawTo(can);
}

