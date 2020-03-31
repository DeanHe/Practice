package TwoPointers;

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
import java.util.*;

public class LongestSubstringwithAtMostKDistinctCharacters {
	/**
	 * @param s
	 *            : A string
	 * @return : The length of the longest substring that contains at most k
	 *         distinct characters.
	 */
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		// write your code here
		int maxLen = 0;
		Map<Character, Integer> charMap = new HashMap<>();
		int start = 0;
		int len = s.length();
		for (int end = 0; end < len; end++) {
			char end_c = s.charAt(end);
			charMap.put(end_c, charMap.getOrDefault(end_c, 0) + 1);
			if (charMap.size() <= k) {
				maxLen = Math.max(maxLen, end - start + 1);
			} else {
				while (charMap.size() > k) {
					char start_c = s.charAt(start);
					if (charMap.containsKey(start_c)) {
						int count = charMap.get(start_c);
						if (count == 1) {
							charMap.remove(start_c);
						} else {
							charMap.put(start_c, count - 1);
						}
					}
					start++;
				}
			}
		}
		return maxLen;
	}
}
