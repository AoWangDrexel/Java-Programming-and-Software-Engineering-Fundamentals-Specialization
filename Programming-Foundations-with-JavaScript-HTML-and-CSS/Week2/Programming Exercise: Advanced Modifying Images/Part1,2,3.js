
//Part 1

/* Write the green screen algorithm you saw in the lecture video yourself.
 * To make sure you really understand the code that was written in the video,
 * you should write the code yourself without looking at the video unless you
 * get stuck and need to refer back to it for a hint.
 */

var lay1 = new SimpleImage("drewRobert.png");
var bImg = new SimpleImage("nyc-skyline.jpg");
bImg.setSize(lay1.getWidth(),lay1.getHeight());
var combo = new SimpleImage(lay1.getWidth(),lay1.getHeight());

for(var px of lay1.values()){
    if(px.getGreen()>px.getRed()+px.getBlue()){
        var x = px.getX();
        var y = px.getY();
        var bPx = bImg.getPixel(x,y);
        combo.setPixel(x,y, bPx)
    }else{
        combo.setPixel(px.getX(),px.getY(),px);
    }
}
print(combo);

//Part 2

/* Your friend is trying to write a program that draws a square 200 pixels 
 * by 200 pixels and that looks like this square with colors red (red value 255),
 * green (green value 255), blue (blue value 255) and magenta (red value 255 and
 * blue value 255). All other RGB values are set to 0.
 */
var img = new SimpleImage(200,200);
for (var px of img.values()){
  var x = px.getX();
  var y = px.getY();
  if (x < img.getWidth()/2){
    px.setRed(255);
  }
  if (y> img.getHeight()/2){
    px.setBlue(255);
  }
  else if(x >= img.getWidth()/2&&y<=img.getHeight()/2){
    px.setGreen(255);
  }
}
print (img);


//Part 3
/*
 * Write a function named setBlack that has one parameter pixel (representing a single pixel)
 * and returns pixel with its red, green, and blue components changed so that the pixel’s color is black.
 */

/*
 * On the left, we have the original image, and on the right, we have modified the image by giving
 * it a black border that is 10 pixels thick. Note that the image size of the image with the border
 * is the same as the original image because the border is not added around the outside of the original
 * image, instead it covers up some of the original image.
 
 * Work through the seven steps to write this function. Work an example by hand and note the steps you
 * took before translating your algorithm to code. Which pixels should be part of the border? How will
 * you identify those pixels? Once you have identified them, how will you make them black?

 * Now you will write another function named addBorder. This function will add a black border to an 
 * image, such as in the following example:
 */
var img = new SimpleImage("smallpanda.png");

function setBlack(px){
    px.setRed(0);
    px.setGreen(0);
    px.setBlue(0);
    return px;
}

function setBlank(img, borderPx){
    for (var px of img.values()){
    var x = px.getX();
    var y = px.getY();
    var imgW = img.getWidth();
    var imgH = img.getHeight();
        if(x < borderPx||y<borderPx||y>imgH-borderPx||x>imgW-borderPx){
           setBlack(px);
        }
    }
    print(img);
}
setBlank(img, 10);
