package twoPointers.window;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in Time complexity O(n).
also the substring from S must maintain the character order in T.

Example 1:
Input: s = "abcdebdde", t = "bde"
Output: "bcde"
Explanation: The minimum window substring "bcde" includes 'b', 'd', and 'e' from string t.

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

public class MinimumWindowSubstringFb {
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
				String candidate = s.substring(start, end);
				if(sameOrder(candidate, t)){
					if(candidate.length() < minLen){
						res = candidate;
						minLen = res.length();
					}
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

	private boolean sameOrder(String s, String t) {
		int j = 0;
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == t.charAt(j)){
				j++;
			}
			if(j == t.length()){
				return true;
			}
		}
		if(j == t.length()){
			return true;
		}
		return false;
	}

	public String minWindowDP(String s, String t) {
		String res = "";
		if(s == null || s.length() == 0 || t == null || t.length() == 0){
			return res;
		}
		int sLen = s.length(), tLen = t.length(), minLen = sLen + 1; // handle res can be s case
		int[][] dp = new int[tLen + 1][sLen + 1];
		TreeSet<Integer> first = new TreeSet<>();
		for(int i = 0; i < sLen; i++){
			if(s.charAt(i) == t.charAt(0)){
				first.add(i);
			}
		}
		for(int i = 0; i < tLen; i++){
			for(int j = 0; j < sLen; j++){
				if(t.charAt(i) == s.charAt(j)){
					dp[i + 1][j + 1] = dp[i][j] + 1;
					if(dp[i + 1][j + 1] == tLen){
						int startIdx = first.floor(j);
						if(startIdx + j - 1 <= minLen){
							minLen = startIdx + j - 1;
							res = s.substring(startIdx, j + 1);
						}
					}
				} else {
					dp[i + 1][j + 1] = dp[i + 1][j];
				}
			}
		}
		return res;
	}
}
