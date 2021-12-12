package dp;
/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false


Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.

analysis:
TC: O(N ^ 2)
*/
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
