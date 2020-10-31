package TwoPointers.Window;

import java.util.HashMap;
import java.util.Map;

/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

Example
Example 1
Input: “eceba”
Output: 3
Explanation:
T is "ece" which its length is 3.
Example 2
Input: “aaa”
Output: 3
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    /**
     * @param s: a string
     * @return: the length of the longest substring T that contains at most 2 distinct characters
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0, counter = 0, res = 0;
        while (end < s.length()) {
            char end_c = s.charAt(end);
            map.put(end_c, map.getOrDefault(end_c, 0) + 1);
            end++;
            while (counter > 2) {
                char start_c = s.charAt(start);
                map.put(start_c, map.get(start_c) - 1);
                if (map.get(start_c) == 0) {
                    map.remove(start_c);
                }
                start++;
            }
            res = Math.max(res, end - start);
        }
        return res;
    }
}
