package dp;

/*Imagine you have a special keyboard with the following keys:

Key 1: (A): Print one 'A' on screen.
Key 2: (Ctrl-A): Select the whole screen.
Key 3: (Ctrl-C): Copy selection to buffer.
Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.
Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

Example
Example 1:

Input: 3
Output: 3
Explanation: A, A, A
Example 2:

Input: 7
Output: 9
Explanation: A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
Notice
1 <= N <= 50
Answers will be in the range of 32-bit signed integer.

the optimal sequence A count mem[i] comes from:
1 continuous A
2 .... Ctrl-A, Ctrl-C, multiple Ctrl-V
*/

public class FourKeysKeyboard {
	/**
     * @param N: an integer
     * @return: return an integer
     */
    public int maxA(int N) {
        // write your code here
    	int[] dp = new int[N];
    	dp[0] = 1;
    	for(int i = 1; i < N; i++){
    		dp[i] = dp[i - 1] + 1;
    		for(int j = 0; j <= i - 3; j++){
    			dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
    		}
    	}
    	return dp[N - 1];
    }
}
