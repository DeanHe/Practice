package dp;
/*
A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)
We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.
Return the minimum number of flips to make S monotone increasing.

Example 1:

Input: "00110"
Output: 1
Explanation: We flip the last digit to get 00111.
Example 2:

Input: "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.
Example 3:

Input: "00011000"
Output: 2
Explanation: We flip to get 00000000.

Constraints:

1 <= s.length <= 10^5
s[i] is either '0' or '1'.

analysis:
dp0[i] means minimum # of flips to make A[:i] monotone and A[i] is 0.
*/
public class FlipStringToMonotoneIncreasing {
	public int minFlipsMonoIncr(String s) {
        int len = s.length();
        int[] dp0 = new int[len + 1];
        int[] dp1 = new int[len + 1];
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == '0'){
                dp0[i + 1] = dp0[i];
                dp1[i + 1] = Math.min(dp0[i], dp1[i]) + 1;
            } else {
                dp0[i + 1] = dp0[i] + 1;
                dp1[i + 1] = Math.min(dp0[i], dp1[i]);
            }
        }
        return Math.min(dp0[len], dp1[len]);
    }
}
