package DP;
/*Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?

Example 1:

Input: N = 10, K = 1, W = 10
Output: 1.00000
Explanation:  Alice gets a single card, then stops.
Example 2:

Input: N = 6, K = 1, W = 10
Output: 0.60000
Explanation:  Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.
Example 3:

Input: N = 21, K = 17, W = 10
Output: 0.73278
Note:

0 <= K <= N <= 10000
1 <= W <= 10000
Answers will be accepted as correct if they are within 10^-5 of the correct answer.
The judging time limit has been reduced for this question.*/
public class New21Game {
	public double new21Game(int N, int K, int W) {
		if(K == 0 || K + W <= N){
			return 1.0;
		}
		int max = K + W - 1;
        double[] dp = new double[max + 1];
        // dp[x] means probability of get x point
        // dp[i] = (dp[i - W] + dp[i - W + 1] + ... + dp[i - 1]) / W 
        // could be simplified to dp[i] = (sum[i - 1] - sum[i - W - 1]) / W.
        //  if we use dp[i] to directly represent the sum[i], we can get dp[i] = dp[i - 1] + (dp[i - 1] - dp[i - W - 1]) / W.
        dp[0] = 1;
        // s = dp[K + 1] + dp[K + 2] + .... + dp[K + W];
        for(int i = 1; i <= max; i++){
        	dp[i] = dp[i - 1];
        }
        return dp[N] - dp[K - 1];
    }
}
