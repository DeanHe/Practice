package sort;

import java.util.ArrayList;
import java.util.Collections;

/*A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example
Given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is minimal. So return 6.*/
public class BestMeetingPoint {
	/**
     * @param grid: a 2D grid
     * @return: the minimize travel distance
     */
    public int minTotalDistance(int[][] grid) {
        // Write your code here
    	int rows = grid.length;
    	int cols = grid[0].length;
    	ArrayList<Integer> rowList = new ArrayList<>();
    	ArrayList<Integer> colList = new ArrayList<>();
    	for(int r = 0; r < rows; r++) {
    		for(int c = 0; c < cols; c++) {
    			if(grid[r][c] == 1) {
    				rowList.add(r);
    				colList.add(c);
    			}
    		}
    	}
    	int sum = 0;
    	for(Integer i : rowList) {
    		sum += Math.abs(i - rowList.get(rowList.size() / 2));
    	}
    	Collections.sort(colList);
    	for(Integer i : colList) {
    		sum += Math.abs(i - colList.get(colList.size() / 2));
    	}
    	return sum;
    }
}
