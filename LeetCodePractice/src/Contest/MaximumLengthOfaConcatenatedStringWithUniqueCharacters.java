package Contest;

import java.util.*;

/*
Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
Return the maximum possible length of s.

Example 1:
Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.

Example 2:
Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".

Example 3:
Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
*/
public class MaximumLengthOfaConcatenatedStringWithUniqueCharacters {
	int res = 0;
	public int maxLength(List<String> arr) {
        dfs(arr, 0, "");
        return res;
    }
	private void dfs(List<String> arr, int idx, String cand) {
		int len = arr.size();
		boolean isUnique = isUnique(cand);
		if(isUnique) {
			res = Math.max(res, cand.length());
		}
		if(idx == len || !isUnique) {
			return;
		}
		res = Math.max(res, cand.length());
		for(int i = idx + 1; i < len; i++) {
			dfs(arr, i, cand + arr.get(i));
		}
	}
	
	private boolean isUnique(String s) {
		int[] map = new int[26];
		char[] arr = s.toCharArray();
		for(char c : arr) {
			map[c - 'a']++;
		}
		for(char c : arr) {
			if(map[c - 'a'] > 1) {
				return false;
			}
		}
		return true;
	}
}
