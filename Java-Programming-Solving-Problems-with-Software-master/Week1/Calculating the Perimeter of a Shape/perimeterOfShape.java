import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public static double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim += currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numOfPoints = 0;
        for (Point pt: s.getPoints()){
            numOfPoints++;
        }
        return numOfPoints;
    }

    public double getAverageLength(Shape s) {
        return getPerimeter(s)/getNumPoints(s);
    }
    
    public void printSideLength(Shape s){
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()){
            double distance = prevPt.distance(currPt);
            System.out.println("distance: " + distance);
            prevPt = currPt;
        }
    }
    
    public void printAllFilePerimeter(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            System.out.println("File name : " + f.getName() + " Perimeter: " + getPerimeter(s));
        }
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0;
        Point prevPt = s.getLastPoint();
        
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            if(currDist > largestSide){
                largestSide = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        int largestX = Integer.MIN_VALUE;
        for (Point pt : s.getPoints()){
            if(pt.getX()>largestX){
                largestX = pt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPeri = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s  = new Shape(fr);
            if(getPerimeter(s) > largestPeri){
                largestPeri = getPerimeter(s);
            }
        }
        return largestPeri;
    }

    public String getFileWithLargestPerimeter() {
        double largestPeri = 0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s  = new Shape(fr);
            if(getPerimeter(s) > largestPeri){
                largestPeri = getPerimeter(s);
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Longest Side: " + getLargestSide(s));
        System.out.println("Largest X: " + getLargestX(s));
        printSideLength(s);
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("Largest Perimeter from files: " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("File name: " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public static void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+ peri);
        
        
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public static void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}

