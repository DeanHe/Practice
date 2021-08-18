package twoPointers.window;

import java.util.HashMap;
import java.util.Map;

/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

Constraints:

m == s.length
n == t.length
1 <= m, n <= 10^5
s and t consist of uppercase and lowercase English letters.


Follow up: Could you find an algorithm that runs in O(m + n) time?
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
*/

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        String res = "";
        if(s == null || s.length() == 0 || t == null || t.length() == 0){
        	return res;
        }
		int sLen = s.length(), minLen = sLen + 1; // handle res can be s case
		int start = 0, end = 0;
        Map<Character, Integer> map = new HashMap<>(); // character in t : count
		for(char c : t.toCharArray()){
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
        int counter = map.size();
        while (end < sLen){
        	char end_c = s.charAt(end);
        	if(map.containsKey(end_c)){
        		map.put(end_c, map.get(end_c) - 1);
        		if(map.get(end_c) == 0){
        			counter--;
				}
			}
        	end++;
        	while(counter == 0){
				if(end - start < minLen){
					minLen = end - start;
					res = s.substring(start, end);
				}
        		char start_c = s.charAt(start);
        		if(map.containsKey(start_c)){
        			map.put(start_c, map.get(start_c) + 1);
        			if(map.get(start_c) == 1){
        				counter++;
					}
				}
        		start++;
			}
		}
        return res;
    }
}
