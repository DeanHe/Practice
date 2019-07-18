package TwoPointers;

import java.util.HashMap;
import java.util.Map;

/*Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.*/

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
        int count = 0;
        for(int r = 0; r < slen; r++){
        	char c = s.charAt(r);
        	if(map.containsKey(c)){
        		map.put(c, map.get(c) - 1);
        		if(map.get(c) >= 0){
        			count++;
        		}
        	}
        	while (count == tlen) {
				char left = s.charAt(l);
				if(map.containsKey(left)){
					map.put(left, map.get(left) + 1); // compensate back the character left
					if(map.get(left) > 0){
						if(minLen > r - l + 1){
							minLen = r - l + 1;
							res = s.substring(l, r + 1);
						}
						count--;
					}
				}
				l++;
			}
        }
        return res;
    }
}
