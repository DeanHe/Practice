package TwoPointers.Window;

/*
Given a string S, find the length of the longest substring T that contains at most k distinct characters.

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
O(n) time
*/

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringwithAtMostKDistinctCharacters {
    /**
     * @param s : A string
     * @return : The length of the longest substring that contains at most k
     * distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int res = 0;
        int slen = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0;
        while(end < slen){
            char end_c = s.charAt(end);
            map.put(end_c, map.getOrDefault(end_c, 0) + 1);
            end++;
            while (map.size() > k){
                char start_c = s.charAt(start);
                map.put(start_c, map.get(start_c) - 1);
                if(map.get(start_c) == 0){
                    map.remove(start_c);
                }
                start++;
            }
            res = Math.max(res, end - start);
        }
        return res;
    }
}
