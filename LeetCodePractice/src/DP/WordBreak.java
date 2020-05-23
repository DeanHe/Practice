package DP;
/*Given a string s and a dictionary of words dict, determine if s can be break into a space-separated sequence of one or more dictionary words.

Example
Given s = "lintcode", dict = ["lint", "code"].

Return true because "lintcode" can be break as "lint code".*/
import java.util.*;

public class WordBreak {
	/**
     * @param s: A string s
     * @param dict: A dictionary of words dict
     */
    public boolean wordBreak(String s, Set<String> dict) {
        // write your code here
        //dp[i] means s[:i] can break
        if(s == null || s.length() == 0){
            return true;
        }
        int slen = s.length();
        int maxLen = 0;
        for(String str : dict){
            maxLen = Math.max(maxLen, str.length());
        }
        boolean[] dp = new boolean[slen + 1];
        dp[0] = true;
        for(int i = 1; i <= slen; i++){
            for(int j = 0; j < i; j++){
                if(i - j > maxLen){
                    j = i - maxLen;
                }
                if(dp[j]){
                    if(dict.contains(s.substring(j, i))){
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[slen];
    }
}
