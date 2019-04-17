package Math;

import java.util.*;

/*Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily picks an integer point in the space covered by the rectangles.

Note:

An integer point is a point that has integer coordinates. 
A point on the perimeter of a rectangle is included in the space covered by the rectangles. 
ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer coordinates of the bottom-left corner, and [x2, y2] are the integer coordinates of the top-right corner.
length and width of each rectangle does not exceed 2000.
1 <= rects.length <= 100
pick return a point as an array of integer coordinates [p_x, p_y]
pick is called at most 10000 times.
Example 1:

Input: 
["Solution","pick","pick","pick"]
[[[[1,1,5,5]]],[],[],[]]
Output: 
[null,[4,1],[4,1],[3,3]]
Example 2:

Input: 
["Solution","pick","pick","pick","pick","pick"]
[[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
Output: 
[null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array of rectangles rects. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.
*/
public class RandomPointInNonoverlappingRectangles {
	
	TreeMap<Integer, Integer> map;
    int[][] arrays;
    int sum;
    Random rnd= new Random();
    
    public RandomPointInNonoverlappingRectangles(int[][] rects) {
    	arrays = rects;
        map = new TreeMap<>();
        sum = 0;
        
        for(int i = 0; i < rects.length; i++) {
            int[] rect = rects[i];
						
            // the right part means the number of points can be picked in this rectangle
            sum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
			
            map.put(sum, i);
        }
    }
    
    public int[] pick() {
    	// nextInt(sum) returns a num in [0, sum -1]. After added by 1, it becomes [1, sum]
        int c = map.ceilingKey( rnd.nextInt(sum) + 1);
        
        return pickInRect(arrays[map.get(c)] );
    }
    
    private int[] pickInRect(int[] rect) {
        int x1 = rect[0], x2 = rect[2], y1 = rect[1], y2 = rect[3];
        
        return new int[]{x1 + rnd.nextInt(x2 - x1 + 1), y1 + rnd.nextInt(y2 - y1 + 1) };
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */
