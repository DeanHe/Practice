package twoPointers.window;

import java.util.HashMap;
import java.util.Map;

/*
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:
Input:
s = "aaabb", k = 3
Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        int res = 0;
        for(int unique = 1; unique <= 26; unique++){
            res = Math.max(res, dfs(s, k, unique));
        }
        return res;
    }

    private int dfs(String s, int k, int unique) {
        int res = 0, len = s.length(), start = 0, end = 0, cntGreaterThanK = 0;
        Map<Character, Integer> map = new HashMap<>();
        while(end < len){
            char c_end = s.charAt(end);
            map.put(c_end, map.getOrDefault(c_end, 0) + 1);
            if(map.get(c_end) == k){
                cntGreaterThanK++;
            }
            end++;
            while (map.size() > unique){
                char c_start = s.charAt(start);
                if(map.get(start) == k){
                    cntGreaterThanK--;
                }
                map.put(c_start, map.get(c_start) - 1);
                if(map.get(c_start) == 0){
                    map.remove(c_start);
                }
                start++;
            }
            if(cntGreaterThanK == unique){ // map.size() == unique == cntGreaterThanK
                res = Math.max(res, end - start);
            }
        }
        return res;
    }
}
