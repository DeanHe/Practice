package TwoPointers.Window;

import java.util.HashMap;
import java.util.Map;

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
		Map<Character, Integer> map = new HashMap<>();
		int start = 0, end = 0, dup = 0, res = 0;
		while (end < s.length()) {
			char end_c = s.charAt(end);
			map.put(end_c, map.getOrDefault(end_c, 0) + 1);
			if (map.get(end_c) == 2) {
				dup++;
			}
			end++;
			while (dup > 0) {
				char start_c = s.charAt(start);
				map.put(start_c, map.get(start_c) - 1);
				if (map.get(start_c) == 1) {
					dup--;
				}
				start++;
			}
			res = Math.max(res, end - start);
		}
		return res;
	}

	public int lengthOfLongestSubstringII(String s) {
		int len =s.length();
		if(len == 0){
			return 0;
		}
		// map from character's ASCII to its last occured index
		int[] freq = new int[256];
		int maxLen = 0;
		int l = 0;
		int r = 0;
		for(l = 0; l < len; l++){
			while(r < len && freq[s.charAt(r)] == 0){
				freq[s.charAt(r)]++;
				maxLen = Math.max(maxLen, r - l + 1);
				r++;
			}
			freq[s.charAt(l)]--;
		}
		return maxLen;
	}
}
