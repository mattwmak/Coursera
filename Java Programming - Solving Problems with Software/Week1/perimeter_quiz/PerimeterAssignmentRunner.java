import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public int getNumPoints (Shape s) {
        //Method shall return integer that is number of points in Shape S.
        int x=0;
        for(Point currPt: s.getPoints()){
            x++;}   
        return x;
    }

    public double getAverageLength(Shape s) {
        // that has one parameter s that is of type Shape. 
        //This method returns a number of type double that is the calculated average of all the sides’ lengths in the Shape S.
        double avg = getPerimeter(s)/getNumPoints(s);
        return avg;
    }

    public double getLargestSide(Shape s) {
        //This method returns a number of type double that is the longest side in the Shape S.
        
        double x=0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()){
            double Dist = prevPt.distance(currPt);
            if(Dist > x){
                x=Dist;
            }
            prevPt = currPt;
        }
        return x;
    }

    public double getLargestX(Shape s) {
        //This method returns a number of type double that is the largest x value over all the points in the Shape S.
        double x=0;
        
        for(Point currPt : s.getPoints()){
            int value = currPt.getX();
            if(value > x){
                x=value;
            }
        }
        return x;
    }

    public double getLargestPerimeterMultipleFiles() {
        //Method creates a Directory Resource and iterates files. 
		//Method will then return largest perimeter
		double final_length=0;
		int x=1;
		DirectoryResource dr = new DirectoryResource();
		
		for(File f : dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			Shape s = new Shape(fr);
			double length = getPerimeter(s);
			System.out.println("current perimeter of dataset #"+x+" "+length);
			if(length>final_length){final_length=length;}
			x++;
		}
		
        return final_length;
    }

    public String getFileWithLargestPerimeter() {
		//This method should, like the getLargestPerimeterMultipleFiles method, create its own DirectoryResource
		//However, this new method returns the File that has the largest such perimeter, so it has return type File
		String temp = null;    // replace this code
		double final_length=0;
		
		DirectoryResource dr = new DirectoryResource();
		
		for(File f : dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			Shape s = new Shape(fr);
			double length = getPerimeter(s);
			if(length>final_length){final_length=length; temp=f.getName();}
		}
        return temp;
    }

    public void testPerimeterMultipleFiles() {
        //method calls getLargestPerimeterMultipleFiles and prints largest perimeter.
		//You will select files when you run this method.		
		double final_length = getLargestPerimeterMultipleFiles();
		System.out.println("largest perimeter = "+final_length);
    }

    public void testFileWithLargestPerimeter() {
        //calls getFileWithLargestPerimeter. For the File that is returned, print the name of that file.
		String name = getFileWithLargestPerimeter();
		System.out.println("largest perimeter contained in "+ name);
    }

    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;                        //Start with totalPerim = 0
        Point prevPt = s.getLastPoint();                // Start wth prevPt = the last point 
        for (Point currPt : s.getPoints()) {            // For each point currPt in the shape, 
            double currDist = prevPt.distance(currPt);  // Find distance from prevPt point to currPt
            totalPerim = totalPerim + currDist;         // Update totalPerim by currDist
            prevPt = currPt;                            // Update prevPt to be currPt
        }
        return totalPerim;                              // totalPerim is the answer
    }   
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int nmPts = getNumPoints(s);
        System.out.println("number of points " + nmPts);    //add additional code to call getNumPoints and print result.
        double avgln = getAverageLength(s); 
        System.out.println("average length: " + avgln);     //add additional code to call getAverageLength and to print out the result.
        double lg_Side = getLargestSide(s);
        System.out.println("largest Side: " + lg_Side);     //add additional code to call getLargestSide and to print out the result.
        double lg_x = getLargestX(s);
        System.out.println("largest X: " + lg_x);           //call the method getLargestX and to print out the result.
    }
    
    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
		pr.testPerimeterMultipleFiles();
		pr.testFileWithLargestPerimeter();
    }
}
