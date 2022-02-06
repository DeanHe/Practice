package greedy;

import java.util.Arrays;

/*
There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

Example 1:
Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.

Note:
1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000

analysis:
sort: TC: O(N log N)
*/
public class TwoCityScheduling {
	public int twoCitySchedCost(int[][] costs) {
        int N = costs.length / 2;
        Arrays.sort(costs, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));
        int res = 0;
        for(int i = 0; i < costs.length; i++){
        	if(i < N){
        		res += costs[i][0];
        	} else {
        		res += costs[i][1];
        	}
        }
        return res;
    }
	public int twoCitySchedCostDP(int[][] costs) {
        int N = costs.length / 2;
        int[][] dp = new int[N + 1][N + 1];
        for(int i= 1; i <= N; i++){
            dp[0][i] = dp[0][i - 1] + costs[i - 1][1];
            dp[i][0] = dp[i - 1][0] + costs[i - 1][0];
        }
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                dp[i][j] = Math.min(dp[i - 1][j] + costs[i + j - 1][0], dp[i][j - 1] + costs[i + j - 1][1]);
            }
        }
        return dp[N][N];
    }
}
