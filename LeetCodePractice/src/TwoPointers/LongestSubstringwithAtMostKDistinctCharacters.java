package TwoPointers;

/*Given a string S, find the length of the longest substring T that contains at most k distinct characters.

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
O(n) time*/
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
			char endChar = s.charAt(end);
			charMap.put(endChar, charMap.getOrDefault(endChar, 0) + 1);
			if (charMap.size() <= k) {
				maxLen = Math.max(maxLen, end - start + 1);
			} else {
				while (charMap.size() > k) {
					char startChar = s.charAt(start);
					if (charMap.containsKey(startChar)) {
						int count = charMap.get(startChar);
						if (count == 1) {
							charMap.remove(startChar);
						} else {
							charMap.put(startChar, count - 1);
						}
					}
					start++;
				}
			}
		}
		return maxLen;
	}
}
