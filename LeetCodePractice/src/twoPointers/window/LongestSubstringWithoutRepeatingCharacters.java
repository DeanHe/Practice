package twoPointers.window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {
	/**
	 * @param s:
	 *            a string
	 * @return: an integer
	 */
	public int lengthOfLongestSubstring(String s) {
		int res = 0, len = s.length(), start = 0, end = 0;
		Set<Character> set = new HashSet<>();
		while(end < len){
			char ec = s.charAt(end);
			while(set.contains(ec)){
				char sc = s.charAt(start);
				set.remove(sc);
				start++;
			}
			set.add(ec);
			end++;
			res = Math.max(res, end - start);
		}
		return res;
	}

	public int lengthOfLongestSubstringII(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}
		int res = 0, len = s.length(), start = 0, end = 0;
		Map<Character, Integer> map = new HashMap<>();
		while(end < len){
			char ec = s.charAt(end);
			if(map.containsKey(ec)){
				if(start < map.get(ec) + 1){
					start = map.get(ec) + 1;
				}
			}
			res = Math.max(res, end - start + 1);
			map.put(ec, end);
			end++;
		}
		return res;
	}
}
