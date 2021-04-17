package dp;
/*A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)
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
Explanation: We flip to get 00000000.*/
public class FlipStringToMonotoneIncreasing {
	public int minFlipsMonoIncr(String S) {
        int len = S.length();
        int[] dp0 = new int[len]; // minimum flip to make A[:i] montone and A[i] is 0;
        int[] dp1 = new int[len];
        dp0[0] = S.charAt(0) == '0' ? 0 : 1;
        dp1[0] = S.charAt(0) == '0' ? 1 : 0;
        for(int i = 1; i < len; i++){
        	dp0[i] = dp0[i - 1] + (S.charAt(i) == '0' ? 0 : 1);
        	dp1[i] = Math.min(dp0[i - 1], dp1[i - 1]) + (S.charAt(i) == '0' ? 1 : 0);
        }
        return Math.min(dp0[len - 1], dp1[len - 1]);
    }
}
