//PART 1

/* Write a JavaScript program that modifies an image by
 * putting three vertical stripes on it - a red stripe 
 * on the left one third, a green stripe in the middle,
 * and a blue stripe on the right one third. For example,
 * if your program ran on Drew’s picture shown on the left,
 * the resulting image would have red, green and blue vertical
 * stripes as shown in the image on the right.
 */ 

var img = new SimpleImage("rodger.png");

for(var pixel of img.values()){
    var width = img.getWidth();
    if(pixel.getX()<=width/3){
        pixel.setRed(255);
    }else if(pixel.getX()>= width/3 && pixel.getX()<=width-width/3){
        pixel.setGreen(255);
    }else{
        pixel.setBlue(255);
    }
}
print(img);

//PART 2
/*
 * Write a JavaScript function named swapRedGreen with one parameter
 * pixel (representing a single pixel). This function should swap the
 * red and green values of the pixel. For example, if you have a pixel
 * with red = 255, green = 100, blue = 150, after calling swapRedGreen
 * on that pixel its new RGB values would be red = 100, green = 255, blue = 150.
*/
 
function swapRedGreen(img){
    for(var pixel of img.values()){
        var tempRed = pixel.getRed();
        var tempGreen = pixel.getGreen();
        pixel.setGreen(tempRed);
        pixel.setRed(tempGreen);
    }
    print(img);
}

swapRedGreen(new SimpleImage("drewgreen.png"));

//PART 3

//Write code to change the Duke blue devil (the image below on the left) to be yellow (as in the image below on the right).

var img = new SimpleImage("duke_blue_devil.png");

for(var pixel of img.values()){
    var rPx = pixel.getRed();
    var gPx = pixel.getGreen();
    var bPx = pixel.getBlue();
    if(rPx == 0&& gPx == 51&&bPx ==227){
        pixel.setRed(255);
        pixel.setGreen(255);
        pixel.setBlue(0);
    }
}
print(img);
