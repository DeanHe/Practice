package TwoPointers;

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
        for(int i = 0; i < tlen; i++){
        	char c = t.charAt(i);
        	map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int minLen = slen + 1; // handle res can be s case
        int l = 0;
        int match = 0;
        for(int r = 0; r < slen; r++){
        	char c_r = s.charAt(r);
        	if(map.containsKey(c_r)){
				if(map.get(c_r) > 0){ //attn
					match++;
				}
        		map.put(c_r, map.get(c_r) - 1);
        	}
        	while (match == tlen) {
				char c_l = s.charAt(l);
				if(minLen > r - l + 1){
					minLen = r - l + 1;
					res = s.substring(l, r + 1);
				}
				if(map.containsKey(c_l)){
					map.put(c_l, map.get(c_l) + 1); // compensate back the character left
					if(map.get(c_l) > 0){ //attn
						match--;
					}
				}
				l++;
			}
        }
        return res;
    }
}
