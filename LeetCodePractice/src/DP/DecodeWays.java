package DP;
/*A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

Example
Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as AB (1 2) or L (12).
Example 2:

Input: "10"
Output: 1*/
public class DecodeWays {
	/**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        if(s == null && s.length() == 0) {
        	return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
      //dp[i] means the s  pre i letters can be decoded # of ways
        dp[0] = 1;
        if(s.charAt(0) == '0') {
        	dp[1] = 0;
        } else {
        	dp[1] = 1;
        }
        for(int i = 2; i <= len; i++) {
        	char nam1 = s.charAt(i - 2);
        	char nam2 = s.charAt(i - 1);
        	if(nam2 != '0') {
        		dp[i] = dp[i - 1];
        	}
        	int val = (nam1 - '0') * 10  + (nam2 - '0');
        	if(val >= 10 && val <= 26) {
        		dp[i] += dp[i - 2];
        	}
        }
        return dp[len];
    }
}
