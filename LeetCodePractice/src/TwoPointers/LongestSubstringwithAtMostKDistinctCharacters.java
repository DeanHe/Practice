package TwoPointers;
/*Given a string S, find the length of the longest substring T that contains at most k distinct characters.

Example
Example 1:

Input: S = "eceba" and k = 3
Output: 4
Explanation: T = "eceb"
Example 2:

Input: S = "WORLD" and k = 4
Output: 4
Explanation: T = "WORL" or "ORLD"
Challenge
O(n) time*/
import java.util.*;

public class LongestSubstringwithAtMostKDistinctCharacters {
	/**
     * @param s : A string
     * @return : The length of the longest substring 
     *           that contains at most k distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int maxLen = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        int start = 0, end = 0;
        int len = s.length();
        for(start = 0; start < len; start++){
            while(end < len){
                char endChar = s.charAt(end);
                if(charMap.containsKey(endChar)){
                    int count = charMap.get(endChar);
                    charMap.put(endChar, count + 1);
                } else {
                    if(charMap.size() == k){
                        break;
                    }
                    charMap.put(endChar, 1);
                }
                end++;
            }
            maxLen = Math.max(maxLen, end - start);
            char startChar = s.charAt(start);
            if(charMap.containsKey(startChar)){
                int count = charMap.get(startChar);
                if(count > 1){
                    charMap.put(startChar, count - 1);
                } else {
                    charMap.remove(startChar);
                }    
            }
        }
        return maxLen;
    }
}
