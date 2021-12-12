package dp;

/*We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.

XX  <- domino

XX  <- "L" tromino
X
Given N, how many ways are there to tile a 2 x N board? Return your answer modulo 10^9 + 7.

(In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.)

Example:
Input: 3
Output: 5
Explanation: 
The five different ways are listed below, different letters indicates different tiles:
XYZ XXZ XYY XXY XYY
XYZ YYZ XZZ XYY XXY

Constraints:
1 <= n <= 1000

analysis:
https://www.youtube.com/watch?v=S-fUTfqrdq8
dp[i][0]: ways to cover i cols, both rows of i-th col are covered
dp[i][1]:  ways to cover i cols, only top row of i-th col is covered
dp[i][2]:  ways to cover i cols, only bottom row of i-th col is covered

TC O(N) SC(N)
*/
public class DominoAndTrominoTiling {
	public int numTilings(int N) {
		int MOD = (int)(1e9 + 7);
		long[][] dp = new long[N + 1][3];
		dp[0][0] = 1;
		dp[1][0] = 1;
		for(int i = 2; i <= N; i++){
			dp[i][0] = (dp[i - 1][0] + dp[i - 2][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
			dp[i][1] = (dp[i - 1][2] + dp[i - 2][0]) % MOD;
			dp[i][2] = (dp[i - 1][1] + dp[i - 2][0]) % MOD;
		}
		return (int)dp[N][0];
	}
}
