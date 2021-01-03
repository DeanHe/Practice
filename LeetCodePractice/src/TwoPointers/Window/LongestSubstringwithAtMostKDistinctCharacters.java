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
     * @param str : A string
     * @return : The length of the longest substring that contains at most k
     * distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String str, int k) {
        // write your code here
        int res = 0;
        int len = str.length();
        Map<Character, Integer> map = new HashMap<>();
        int s = 0, e = 0;
        while(e < len){
            char ec = str.charAt(e);
            map.put(ec, map.getOrDefault(ec, 0) + 1);
            e++;
            while (map.size() > k){
                char sc = str.charAt(s);
                map.put(sc, map.get(sc) - 1);
                if(map.get(sc) == 0){
                    map.remove(sc);
                }
                s++;
            }
            res = Math.max(res, e - s);
        }
        return res;
    }
}
