package DP;

public class WildcardMatching {
	/**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        // write your code here
        int sLen = s.length();
        int pLen = p.length();
        //where dp[i][j] means the first i characters in string s matches the first characters of string p. 
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        //init
        dp[0][0] = true;
        for(int j = 1; j <= pLen; j++){
            if(p.charAt(j - 1) == '*'){
                dp[0][j] = dp[0][j - 1];    
            }
        }
        /*
        Transit function: 
        -- If p.charAt(j - 1) != '*', then dp[i][j] = dp[i - 1][j - 1] IFF s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'
        -- If p.charAt(j - 1) == '*', then 
             -- dp[i][j] = dp[i - 1][j - 1] || // Match 1 character
                           = dp[i][j - 1] || // Match 0 character
                           = dp[i - 1][j] // Match any sequence of characters 
        */
        for(int i = 1; i <= sLen; i++){
            for(int j = 1; j <= pLen; j++){
                if(p.charAt(j - 1) != '*'){
                    if(p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1]; 
                }
            }
        }
        return dp[sLen][pLen];
     }
}
