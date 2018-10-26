package DP;

import java.util.*;

public class WordBreak {
	/**
     * @param s: A string s
     * @param dict: A dictionary of words dict
     */
    public boolean wordBreak(String s, Set<String> dict) {
        // write your code here
        //dp[i] means front ith substring can break 
        if(s == null || s.length() == 0){
            return true;
        }
        int len = s.length();
        int maxLen = 0;
        for(String str : dict){
            maxLen = Math.max(maxLen, str.length());
        }
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for(int i = 1; i <= len; i++){
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
        return dp[len];
    }
}
