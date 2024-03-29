package dp;

/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

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
Output: 1
*/
public class DecodeWays {
    /**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        if (s == null && s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        // init
        dp[0] = 1;
        if (s.charAt(0) == '0') {
            dp[1] = 0;
        } else {
            dp[1] = 1;
        }
        // transfer
        for (int i = 2; i <= len; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[len];
    }

    // dfs + memorization
    public int numDecodingsDFS(String s) {
        if (s == null && s.length() == 0) {
            return 0;
        }
        int len = s.length();
        Integer[] mem = new Integer[len];
        return dfs(s, len - 1, mem);
    }

    private int dfs(String s, int i, Integer[] mem) {
        if(i < 0){
            return 1;
        }
        if(mem[i] != null){
            return mem[i];
        }
        int res = 0;
        int first = Integer.valueOf(s.substring(i, i + 1));
        if (first >= 1 && first <= 9) {
            res += dfs(s, i - 1, mem);
        }
        if(i >= 1){
            int second = Integer.valueOf(s.substring(i - 1, i + 1));
            if (second >= 10 && second <= 26) {
                res += dfs(s, i - 2, mem);
            }
        }
        return mem[i] = res;
    }
}
