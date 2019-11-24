import java.util.Scanner;
import java.io.*;
/*
 * PROJECT I: Project1.java
 *
 * As in project 0, this file - and the others you downloaded - form a
 * template which should be modified to be fully functional.
 *
 * This file is the *last* file you should implement, as it depends on both
 * Point and Circle. Thus your tasks are to:
 *
 * 1) Make sure you have carefully read the project formulation. It contains
 *    the descriptions of all of the functions and variables below.
 * 2) Write the class Point.
 * 3) Write the class Circle
 * 4) Write this class, Project1. The Results() method will perform the tasks
 *    laid out in the project formulation.
 */
public class Project1 {
    // -----------------------------------------------------------------------
    // Do not modify the names of the variables below! This is where you will
    // store the results generated in the Results() function.
    // -----------------------------------------------------------------------
    public int    circleCounter; // Number of non-singular circles in the file.
    public int    posFirstLast;  // Indicates whether the first and last
                                 // circles overlap or not.
    public double maxArea;       // Area of the largest circle (by area).
    public double minArea;       // Area of the smallest circle (by area).
    public double averageArea;   // Average area of the circles.
    public double stdArea;       // Standard deviation of area of the circles.
    public double medArea;       // Median of the area.
    public int    stamp=472143;
    // -----------------------------------------------------------------------
    // You may implement - but *not* change the names or parameters of - the
    // functions below.
    // -----------------------------------------------------------------------

    /**
     * Default constructor for Project1. You should leave it empty.
     */
    public Project1() {
        // This method is complete.
    }

    /**
     * Results function. It should open the file called FileName (using
     * Scanner), and from it generate the statistics outlined in the project
     * formulation. These are then placed in the instance variables above.
     *
     * @param fileName  The name of the file containing the circle data.
     */
    public void results(String fileName){
        // You need to fill in this method.
    	
    	double x,y,rad;
    	int i =0;
    	this.circleCounter = 0;
    	this.maxArea = Double.MIN_VALUE;
    	this.minArea = Double.MAX_VALUE;
    	
    	try {
    		Scanner counter = new Scanner(new BufferedReader(new FileReader(fileName)));
    		while(counter.hasNext()) {
    			x = counter.nextDouble();
    			y = counter.nextDouble();
    			rad = counter.nextDouble();
    			if (rad > Point.GEOMTOL) {
    				i += 1;
    			}
    		}
    		Circle[] CircleList = new Circle[i];
    		
    		Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
    		
    		while(scanner.hasNext()) {
    			x = scanner.nextDouble();
    			y = scanner.nextDouble();
    			rad = scanner.nextDouble();
    			Circle C = new Circle(x,y,rad);
    			
    			
    			if (rad >= Point.GEOMTOL) {
    				CircleList[circleCounter] = C;
    				this.circleCounter += 1;
    			}
    			if (C.area() > this.maxArea) {
    				this.maxArea = C.area();
    			}
    			if (C.area() < this.minArea) {
    				this.minArea = C.area();
    			}
    		}
    		this.averageArea = averageArea(CircleList);
    		this.stdArea = areaStandardDeviation(CircleList);
    		this.posFirstLast = CircleList[CircleList.length-1].overlap(CircleList[0]);
    		this.medArea = areaMedian(CircleList);
    		
//    		for (int x1 = 0; x1 < CircleList.length; x1++) {
//    			System.out.println(CircleList[x1].toString());
//    		}
    		
    		
    			
    	} catch(Exception e) {
    			System.err.println("An error has occured. See below for details");
    			e.printStackTrace();
    			
    	}
    	
    	System.out.println("Information about the data:");
    	System.out.println("  Number of non-singular circles in file: " + this.circleCounter);
    	System.out.println("  posFirstLast: " + this.posFirstLast);
    	System.out.println("  Max area: " + this.maxArea);
    	System.out.println("  Min area: " + this.minArea);
    	System.out.println("  Average area: " + this.averageArea);
    	System.out.println("  Std area: " + this.stdArea);
    	System.out.println("  Median area: " + this.medArea);
    	
    	
    		
    		
    }

    
    /**
     * A function to calculate the avarage area of circles in the array provided. 
     *
     * @param circles  An array if Circles
     */
    public double averageArea(Circle[] circles) {
      // You need to fill in this method and remove the return 0.0 statement.
    	double areaSum = 0;
    	for (int i=0; i < circles.length; i++) {
    		areaSum += circles[i].area();
    		
    	}
    	
      return (areaSum/this.circleCounter);
    }
    
    /**
     * A function to calculate the standart deviation of areas in the circles in the array provided. 
     *
     * @param circles  An array of Circles
     */
    public double areaStandardDeviation(Circle[] circles) {
    // You need to fill in this method and remove the return 0.0 statement.
    	double areaSumSqrd = 0;
    	for (int i=0; i < circles.length; i++) {
    		
    		areaSumSqrd += Math.pow(circles[i].area(), 2);
    		
    	}
      return Math.sqrt(((areaSumSqrd/this.circleCounter)-Math.pow(this.averageArea, 2)));
    }
    
    public double areaMedian(Circle[] circles) {
    	
    	circles = Sort(circles);
    	medArea = -1;
        int circleMedian = -1;
        if (circleCounter % 2 == 0){
          circleMedian = circleCounter/2;
          medArea = ((circles[circleMedian-1].area() + circles[circleMedian].area()) / 2);
        } 
        else if (circleCounter % 2 != 0) {
          circleMedian = (circleCounter+1)/2;
          medArea = (circles[circleMedian-1].area());
          
        }//end if
        
        
        return medArea;
   
     }// end results
    
    
    private Circle[] Sort(Circle[] Array) {
    	
    	boolean swap = true;
    	Circle temp = new Circle();
    	while (swap){
    		swap = false;
    		for (int i = 0; i < (Array.length -1); i ++) {
    			if (Array[i].area() > Array[i+1].area()) {
    				temp = Array[i];
    				Array[i] = Array[i+1];
    				Array[i+1] = temp;
    				swap = true;
    			}
    		}
    	}
    	
    	return Array;
    }
  
    // =======================================================
    // Tester - tests methods defined in this class
    // =======================================================

    /**
     * Your tester function should go here (see week 14 lecture notes if
     * you're confused). It is not tested by BOSS, but you will receive extra
     * credit if it is implemented in a sensible fashion.
     */
    public static void main(String args[]){
        // You need to fill in this method.
    	String filename = "project1.data";
    	
    	Project1 instance = new Project1();
    	instance.results(filename);
    }
}
