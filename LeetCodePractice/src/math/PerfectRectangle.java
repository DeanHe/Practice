package math;

import java.util.HashSet;

/*
Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. 
(coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).

analysis:
The right answer must satisfy two conditions:

the large rectangle area should be equal to the sum of small rectangles
count of all the points should be even, and that of all the four corner points should be one
*/
public class PerfectRectangle {
	public boolean isRectangleCover(int[][] rectangles) {
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        HashSet<String> set = new HashSet<>();
        int area = 0;
        for(int[] rect : rectangles){
        	x1 = Math.min(x1, rect[0]); // 4 largest corner point
        	y1 = Math.min(y1, rect[1]);
        	x2 = Math.max(x2, rect[2]);
        	y2 = Math.max(y2, rect[3]);
        	String p1 = rect[0] + "" + rect[1];  // rectangle 4 points
        	String p2 = rect[2] + "" + rect[1];
        	String p3 = rect[0] + "" + rect[3];
        	String p4 = rect[2] + "" + rect[3];
        	if(!set.add(p1)){
        		set.remove(p1);
        	}
        	if(!set.add(p2)){
        		set.remove(p2);
        	}
        	if(!set.add(p3)){
        		set.remove(p3);
        	}
        	if(!set.add(p4)){
        		set.remove(p4);
        	}
        	area += (rect[2] - rect[0]) * (rect[3] - rect[1]);
        }
        if(!set.contains(x1 + "" + y1) || !set.contains(x1 + "" + y2) || !set.contains(x2 + "" + y1) || !set.contains(x2 + "" + y2) || set.size() != 4){
        	return false;
        }
        return area == (x2 - x1) * (y2 - y1);
    }
}
