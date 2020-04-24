package DP;
/*There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. 
 * You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... 
Find the minimum cost to paint all houses.

Example
Given costs = [[14,2,11],[11,14,5],[14,3,10]] return 10
house 0 is blue, house 1 is green, house 2 is blue, 2 + 5 + 3 = 10

Notice
All costs are positive integers.*/
public class PaintHouse {
	/**
     * @param costs: n x 3 cost matrix
     * @return: An integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        // write your code here
    	if(costs == null || costs.length == 0){
    		return 0;
    	}
    	int houses = costs.length;
    	int colors = costs[0].length;
    	if(houses == 1){
    		int min = Integer.MAX_VALUE;
    		for(int c : costs[0]){
    			min = Math.min(min, c);
    		}
    		return min;
    	}
    	int[][] dp = new int[houses][colors];
    	// mem[i][j] means paint to house i with color j the min costs
        // init
    	for(int i = 0; i < colors; i++){
    		dp[0][i] = costs[0][i];
    	}
    	// transfer func
    	for(int i = 1; i < houses; i++){
    		for(int c = 0; c < colors; c++){
    			int temp = Integer.MAX_VALUE;
    			for(int ct = 0; ct < colors; ct++){
    				if(c != ct){
    					temp = Math.min(dp[i - 1][ct], temp);
    				}
    			}
    			dp[i][c] = temp + costs[i][c];
    		}
    	}
    	int res = Integer.MAX_VALUE;
    	for(int c = 0; c < colors; c++){
    		res = Math.min(res, dp[houses - 1][c]);
    	}
    	return res;
    }
}
