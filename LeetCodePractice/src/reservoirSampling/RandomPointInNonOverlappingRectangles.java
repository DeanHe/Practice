package reservoirSampling;

import java.util.Random;
import java.util.TreeMap;

/*
Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily picks an integer point in the space covered by the rectangles.

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
public class RandomPointInNonOverlappingRectangles {
    TreeMap<Integer, Integer> map;
    int[][] rects;
    int sum;
    Random rand;
    public RandomPointInNonOverlappingRectangles(int[][] rects) {
        rand = new Random();
        this.rects = rects;
        map = new TreeMap<>();
        sum = 0;
        for(int i = 0; i < rects.length; i++){
            int[] rect = rects[i];
            // total points in the rect
            sum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            map.put(sum, i);
        }
    }

    public int[] pick() {
        int prob = rand.nextInt(sum) + 1;
        int idx = map.ceilingKey(prob);
        return pickInRect(rects[map.get(idx)]);
    }

    private int[] pickInRect(int[] rect){
        int x = rect[0] + rand.nextInt(rect[2] - rect[0] + 1);
        int y = rect[1] + rand.nextInt(rect[3] - rect[1] + 1);
        return new int[]{x, y};
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */
