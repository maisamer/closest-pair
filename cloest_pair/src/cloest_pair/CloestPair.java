package cloest_pair;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CloestPair {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		/*int n;
		File f=new File("input1.txt");
		Scanner s=new Scanner(f);
		n=s.nextInt();
		while(s.hasNextLine())
		{
			String in=s.nextLine();
			String first,second;
	        
		}*/
		int n=12;  //number of points
	    Point[] points = new Point[]{ new Point(22, 7), new Point(4, 13), 
	    		            new Point(10, 7),new Point(5, 5),
	    		            new Point(17, 9), new Point(19, 5),
	    		            new Point(13, 7), new Point(15, 10), 
	    		            new Point(29, 7), new Point(30, 10),
	    		            new Point(2, 14), new Point(25, 2) };
	    Point [] pX=new Point[points.length];
	    Point [] pY=new Point[points.length];
		
	    for(int i=0;i<points.length;i++)
	    {
	    	pX[i]=pY[i]=points[i];
	    }
	    Algorithm a=new Algorithm(points, points.length);
	    a.closest(points, n);
	    //System.out.println(points.length);
	

	}

}
