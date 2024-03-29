package dp;
/*
There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.

Example
Example 1:

Input: n=3, k=2  
Output: 6
Explanation:
          post 1,   post 2, post 3
    way1    0         0       1 
    way2    0         1       0
    way3    0         1       1
    way4    1         0       0
    way5    1         0       1
    way6    1         1       0
Example 2:

Input: n=2, k=2  
Output: 4
Explanation:
          post 1,   post 2
    way1    0         0       
    way2    0         1            
    way3    1         0          
    way4    1         1       
Notice
n and k are non-negative integers.
*/
public class PaintFence {
	/**
     * @param n: non-negative integer, n posts
     * @param k: non-negative integer, k colors
     * @return: an integer, the total number of ways
     */
    public int numWays(int n, int k) {
        // write your code here
    	// mem[i] means # of ways to paint with last two posts same color or different
    	int[] same = new int[n + 1];
    	int[] diff = new int[n + 1];
    	same[1] = 0;
    	diff[1] = k;
    	for(int i = 2; i <= n; i++){
    		same[i] = diff[i - 1];
    		diff[i] = (diff[i - 1] + same[i - 1]) * (k - 1);
    	}
    	return same[n] + diff[n];
    }
}
