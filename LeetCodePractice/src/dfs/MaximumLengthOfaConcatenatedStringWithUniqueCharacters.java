package dfs;

import java.util.*;

/*
Given an array of strings arr. string s is a concatenation of a sub-sequence of arr which have unique characters.
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

tag:bitmask

analysis:
1 can each string in arr if it has unique char, if so, use a mask represents it
2 compare mask with all strings in dp, see if a new concatenation has unique char, if so add to dp
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
		char[] arr = s.toCharArray();
		Set<Character> set = new HashSet<>();
		for(char c : arr){
			if(set.contains(c)){
				return false;
			}
			set.add(c);
		}
		return true;
	}

	// solution 2
	public int maxLengthBitMask(List<String> arr) {
		List<Integer> dp = new ArrayList<>(); // save all unique concatenation candidates
		dp.add(0);
		int res = 0;
		for(String s : arr){
			int mask = 0, duplicate = 0;
			for(char c : s.toCharArray()){
				int bit = 1 << (c - 'a');
				duplicate |= mask & bit;
				mask |= bit;
			}
			if(duplicate > 0){
				continue;
			}
			int len = dp.size();
			for(int i = 0; i < len; i++){
				if((dp.get(i) & mask) > 0){
					continue;   // has duplicate char
				}
				dp.add(dp.get(i) | mask);
				res = Math.max(res, Integer.bitCount(dp.get(i) | mask));
			}
		}
		return res;
	}
}
