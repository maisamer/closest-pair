package cloest_pair;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Comparator;

public class Algorithm {
    private Point[] input;
    private int size;
    private double bestDistance = Double.POSITIVE_INFINITY;
    public Algorithm(Point []input,int size) {
		// TODO Auto-generated constructor stub
    	this.input=input;
    	this.size=size;
	}
    public double cloestPair(Point []px,Point []py,int n)
    {
    	if(n==1)
    		return Double.POSITIVE_INFINITY;
 
    	if(n<=3)
    		return bruteForce(px,n);
        int mid = n/2;
        Point midPoint = px[mid];
        
        Point Pyl[]=new Point[mid+1];   // y sorted points on left of vertical line
        Point Pyr[]=new Point[n-mid-1];
        Point Pxl[]=new Point[mid+1];   // y sorted points on left of vertical line
        Point Pxr[]=new Point[n-mid-1];// y sorted points on right of vertical line
        int li = 0, ri = 0;  // indexes of left and right subarrays
 
        for (int i = 0; i < n; i++)
        {
          if (py[i].getX() <= midPoint.getX())
          {
        	  Pyl[li] =Pxl[li]= py[i];
        	  li++;
          }
          else
          {
        	  Pyr[ri] =Pxr[ri]= py[i];
	    	  ri++;
          }
          
        }
	    sortByXCoordinates(Pxl);
	    sortByXCoordinates(Pxr);
        // Consider the vertical line passing through the middle point
        // calculate the smallest distance dl on left of middle point and
        // dr on right side
        double dl = cloestPair(Pxl, Pyl, mid);
        double dr = cloestPair(Pxr, Pyr, n-mid);
     
        // Find the smaller of two distances
        double d = min(dl, dr);
     
        // Build an array strip[] that contains points close (closer than d)
        // to the line passing through the middle point
        Point strip[]=new Point[n];
        int j = 0;
        for (int i = 0; i < n; i++)
            if (Math.abs(py[i].getX() - midPoint.getX()) < d)
            { 
            	strip[j] = py[i];
            	j++;
            }
     
        // Find the closest points in strip.  Return the minimum of d and closest
        // distance is strip[]
        return min(d, stripClosest(strip, j, d) );
    }
    public double dist(Point p1,Point p2)
    {
        return Math.sqrt( (p1.getX()- p2.getX())*(p1.getX() - p2.getX()) +
                (p1.getY() - p2.getY())*(p1.getY() - p2.getY()));
    }
    public double bruteForce(Point []P,int n)
    {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; ++i)
            for (int j = i+1; j < n; ++j)
                if (dist(P[i], P[j]) < min)
                    min = dist(P[i], P[j]);
        return min;
    }
    public double min(double x, double y)
    {
        return (x < y)? x : y;
    }
    public double stripClosest(Point strip[], int size, double d)
    {
        double min = d;  // Initialize the minimum distance as d
     
        // Pick all points one by one and try the next points till the difference
        // between y coordinates is smaller than d.
        // This is a proven fact that this loop runs at most 6 times
        for (int i = 0; i < size; ++i)
            for (int j = i+1; j < size && (strip[j].getY() - strip[i].getY()) < min; ++j)
                if (dist(strip[i],strip[j]) < min)
                    min = dist(strip[i], strip[j]);
     
        return min;
    }
    public double closest(Point p[], int n)
    {
        Point Px[]=new Point[n];
        Point Py[]=new Point[n];
        for (int i = 0; i < n; i++)
        {
            Px[i] = p[i];
            Py[i] = p[i];
        }
        sortByXCoordinates(Px);
        sortByYCoordinates(Py);
        return cloestPair(Px, Py, n);
    }
	  public static void sortByYCoordinates(Point [] pointsByY){

		   Arrays.sort(pointsByY, new Comparator<Point>() {
			   public int compare(Point a, Point b)
			   {
				    return Integer.compare(a.y, b.y);
			   }
		});
	}
	  public static void sortByXCoordinates(Point[] pxl){

		   Arrays.sort(pxl, new Comparator<Point>() {
			   public int compare(Point a, Point b)
			   {
				    return Integer.compare(a.x, b.x);
			   }
		});
		   }
    

}
