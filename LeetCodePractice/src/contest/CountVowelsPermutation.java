package contest;

/*Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
Each vowel 'a' may only be followed by an 'e'.
Each vowel 'e' may only be followed by an 'a' or an 'i'.
Each vowel 'i' may not be followed by another 'i'.
Each vowel 'o' may only be followed by an 'i' or a 'u'.
Each vowel 'u' may only be followed by an 'a'.
Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:

Input: n = 1
Output: 5
Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
Example 2:

Input: n = 2
Output: 10
Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
Example 3: 

Input: n = 5
Output: 68

Constraints:
1 <= n <= 2 * 10^4

sol:Detailed Explanation using Graphs [With Pictures] [O(n)]
Hence, dp[n+1][x] = sum of all dp[n][y] such that there is a directed edge from y to x.
*/
public class CountVowelsPermutation {
	public int countVowelPermutation(int n) {
		int MOD = (int)(1e9 + 7);
		// 0:a, 1:e, 2:i, 3:o, 4:u
		//dp[i][j] denotes # of path ends with vowel[j]
        long[][] dp = new long[n + 1][5];
        //init
        for(int i = 0; i < 5; i++){
        	dp[1][i] = 1;
        }
        //transfer func
        for(int i = 1; i < n; i++){
        	dp[i + 1][0] = (dp[i][1] + dp[i][2] + dp[i][4]) % MOD;
        	dp[i + 1][1] = (dp[i][0] + dp[i][2]) % MOD;
        	dp[i + 1][2] = (dp[i][1] + dp[i][3]) % MOD;
        	dp[i + 1][3] = (dp[i][2]) % MOD;
        	dp[i + 1][4] = (dp[i][2] + dp[i][3]) % MOD;
        }
        
        long res = 0;
        for(int i = 0; i < 5; i++){
        	res = (res + dp[n][i]) % MOD;
        }
        return (int)res;
    }
}
