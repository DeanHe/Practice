package contest;

/*
You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array or stay in the same place  (The pointer should not be placed outside the array at any time).

        Given two integers steps and arrLen, return the number of ways such that your pointer still at index 0 after exactly steps steps.

        Since the answer may be too large, return it modulo 10^9 + 7.

        Example 1:
        Input: steps = 3, arrLen = 2
        Output: 4
        Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
        Right, Left, Stay
        Stay, Right, Left
        Right, Stay, Left
        Stay, Stay, Stay

        Example 2:
        Input: steps = 2, arrLen = 4
        Output: 2
        Explanation: There are 2 differents ways to stay at index 0 after 2 steps
        Right, Left
        Stay, Stay

        Example 3:
        Input: steps = 4, arrLen = 2
        Output: 8

        Constraints:
        1 <= steps <= 500
        1 <= arrLen <= 10^6
*/
public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    final int mod = (int) (1e9 + 7);

    public int numWays(int steps, int arrLen) {
        int[][] dp = new int[arrLen][steps + 1]; // mem[i][j] means # of ways to reach arr[i] after j steps
        dp[0][0] = 1;
        for(int s = 1; s <= steps; s++){
            for(int i = 0; i < arrLen; i++){
                dp[i][s] = dp[i][s - 1] % mod; // stay
                if(i > 0){
                    dp[i][s] = (dp[i][s] + dp[i - 1][s - 1]) % mod;  // move right
                }
                if(i + 1 < arrLen){
                    dp[i][s] = (dp[i][s] + dp[i + 1][s - 1]) % mod;  // move left
                }
                if(dp[i][s] == 0){
                    break;
                }
            }
        }
        return dp[0][steps];
    }

    public int numWaysWithRollingArray(int steps, int arrLen) {
        int[][] dp = new int[arrLen][2]; // mem[i][j] means # of ways to reach arr[i] after j steps
        dp[0][0] = 1;
        for(int s = 1; s <= steps; s++){
            for(int i = 0; i < arrLen; i++){
                dp[i][s % 2] = dp[i][(s - 1) % 2] % mod; // stay
                if(i > 0){
                    dp[i][s % 2] = (dp[i][s % 2] + dp[i - 1][(s - 1) % 2]) % mod;  // move right
                }
                if(i + 1 < arrLen){
                    dp[i][s % 2] = (dp[i][s % 2] + dp[i + 1][(s - 1) % 2]) % mod;  // move left
                }
                if(dp[i][s % 2] == 0){
                    break;
                }
            }
        }
        return dp[0][steps % 2];
    }
}
