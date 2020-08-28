package TwoPointers.Window;

import java.util.HashMap;
import java.util.Map;

/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
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
        Map<Character, Integer> map = new HashMap<>(); // character in t : count
        int slen = s.length();
        int tlen = t.length();
        for(char c : t.toCharArray()){
        	map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int minLen = slen + 1; // handle res can be s case
        int start = 0, end = 0;
        int counter = map.size();
        while (end < slen){
        	char end_c = s.charAt(end);
        	if(map.containsKey(end_c)){
        		map.put(end_c, map.get(end_c) - 1);
        		if(map.get(end_c) == 0){
        			counter--;
				}
			}
        	end++;
        	while(counter == 0){
        		char start_c = s.charAt(start);
        		if(map.containsKey(start_c)){
        			map.put(start_c, map.get(start_c) + 1);
        			if(map.get(start_c) > 0){
        				counter++;
					}
				}
        		if(minLen > end - start){
        			minLen = end - start;
        			res = s.substring(start, end);
				}
        		start++;
			}
		}
        return res;
    }
}
