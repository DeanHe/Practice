package DP;

import java.util.Arrays;
import java.util.Comparator;
/*
Enter the length l and width w of a matrix, and three points that must pass. 
Ask how many ways you can go from the upper left corner to the lower right corner (every step, 
you can only go right or go down). The input is guaranteed that there is at least one legal path. 
You only need to return the solution number mod 1000000007.

Example
Given l=4, w=4. The three mandatory points are [1,1],[2,2],[3,3]. Return 8.

Explanation:
[1,1]->[1,2]->[2,2]->[2,3]->[3,3]->[3,4]->[4,4]
[1,1]->[1,2]->[2,2]->[2,3]->[3,3]->[4,3]->[4,4]
[1,1]->[1,2]->[2,2]->[3,2]->[3,3]->[3,4]->[4,4]
[1,1]->[1,2]->[2,2]->[3,2]->[3,3]->[4,3]->[4,4]
[1,1]->[2,1]->[2,2]->[2,3]->[3,3]->[3,4]->[4,4]
[1,1]->[2,1]->[2,2]->[2,3]->[3,3]->[4,3]->[4,4]
[1,1]->[2,1]->[2,2]->[3,2]->[3,3]->[3,4]->[4,4]
[1,1]->[2,1]->[2,2]->[3,2]->[3,3]->[4,3]->[4,4]
The sum is 8.
Given l=1, w=5. The three points are [1,2],[1,3],[1,4]. Return 1.

Explanation:
[1,1]->[1,2]->[1,3]->[1,4]->[1,5]
The sum is 1.
Notice
1 <= l, w <= 2000
*/
public class CalculationTheSumOfPath {
	class Point {
	      int x;
	      int y;
	      Point() { x = 0; y = 0; }
	      Point(int a, int b) { x = a; y = b; }
	}
	
	/**
     * @param l: The length of the matrix
     * @param w: The width of the matrix
     * @param points: three points 
     * @return: The sum of the paths sum
     */
    public long calculationTheSumOfPath(int l, int w, Point[] points) {
        long[][] dp = new long[l][w];
        long mod = 1000000007;
        for(int r = 0; r < l; r++){
        	dp[r][0] = 1;
        }
        for(int c = 0; c < w; c++){
        	dp[0][c] = 1;
        }
        for(int r = 1; r < l; r++){
        	for(int c = 1; c < w; c++){
        		dp[r][c] = (dp[r - 1][c] + dp[r][c - 1]) % mod;
        	}
        }
        Arrays.sort(points, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if(o1.x == o2.x){
					return o1.y - o2.y;
				} else {
					return o1.x - o2.x;
				}
			}
		});
        long ans = 0;
        Point a = points[0], b = points[1], c = points[2];
        ans = dp[a.x - 1][a.y - 1];
        ans = ans * dp[b.x -a.x][b.y - a.y] % mod;
        ans = ans * dp[c.x - b.x][c.y - b.y] % mod;
        ans = ans * dp[l - c.x][w - c.y] % mod;
        return ans;
    }
}
