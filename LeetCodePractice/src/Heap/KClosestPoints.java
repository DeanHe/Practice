package Heap;

import java.util.*;

/*Given some points and an origin point in two-dimensional space, find k points which are nearest to the origin.
Return these points sorted by distance, if they are same in distance, sorted by the x-axis, and if they are same in the x-axis, sorted by y-axis.

Example
Example 1:

Input: points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3 
Output: [[1,1],[2,5],[4,4]]
Example 2:

Input: points = [[0,0],[0,9]], origin = [3, 1], k = 1
Output: [[0,0]]*/
public class KClosestPoints {
	/**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // write your code here
    	final Point global_origin = origin;
        PriorityQueue<Point> pq = new PriorityQueue<Point>(k, new Comparator<Point>(){
                @Override
                public int compare(Point a, Point b){
                    int diff = getDistance(b, global_origin) - getDistance(a, global_origin);
                    if(diff == 0){
                        diff = b.x - a.x;
                    }
                    if(diff == 0){
                        diff = b.y - a.y;
                    }
                    return diff;
                }
            });
        
        for(int i = 0; i < points.length; i++){
            pq.offer(points[i]);
            if(pq.size() > k){
                pq.poll();
            }
        }
        k = pq.size();
        Point[] res = new Point[k];
        while(!pq.isEmpty()){
            res[--k] = pq.poll();
        }
        return res;
    }
    
    private int getDistance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
}
