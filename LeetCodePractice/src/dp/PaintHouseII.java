package dp;
/*
There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different. 
 * You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... 
Find the minimum cost to paint all houses.

Example
Given n = 3, k = 3, costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

house 0 is color 2, house 1 is color 3, house 2 is color 2, 2 + 5 + 3 = 10

Challenge
Could you solve it in O(nk)?

Notice
All costs are positive integers.
*/
public class PaintHouseII {
	/**
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        // write your code here
    	int houses = costs.length;
    	if(houses == 0){
    		return 0;
    	}
    	int colors = costs[0].length;
    	if(colors == 0){
    		return 0;
    	}
    	int[][] dp = new int[houses][colors];
    	// init

    	// transfer func
    	int mostMin = 0;
        int secondMostMin = 0;
        int preMostMinColor = -1;
    
    	for(int i = 1; i < houses; i++){
    		int preMostMin = mostMin;
            int preSecondMostMin = secondMostMin;
            int curMostMinColor = -1;
            
            mostMin = Integer.MAX_VALUE;
            secondMostMin = Integer.MAX_VALUE;
            for(int c =0; c < colors; c++){
            	if(c == preMostMinColor){
            		dp[i][c] = preSecondMostMin + costs[i][c];
            	} else {
            		dp[i][c] = preMostMin + costs[i][c];
            	}
            	
            	if(mostMin <= dp[i][c]){
            		secondMostMin = Math.min(secondMostMin, dp[i][c]);
            	} else {
            		secondMostMin = mostMin;
            		mostMin = dp[i][c];
            		curMostMinColor = c;
            	}
            }
            preMostMinColor = curMostMinColor;
    	}
    	return mostMin;
    }
}
