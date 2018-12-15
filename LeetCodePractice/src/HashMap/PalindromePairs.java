package HashMap;

import java.util.*;

import BFS.IsGraphBipartite;

/*Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]*/
public class PalindromePairs {
	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> res = new ArrayList<>();
		if(words == null || words.length <= 1) {
			return res;
		}
		int len = words.length;
		//word : index
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < len; i++) {
			map.put(words[i], i);
		}
		for(int i = 0; i < len; i++) {
			for(int j = 0; j <= words[i].length(); j++) {
				String pre = words[i].substring(0, j);
				String post = words[i].substring(j);
				if(isPalindrome(pre)) {
					String postReverse = new StringBuilder(post).reverse().toString();
					if(map.containsKey(postReverse) && map.get(postReverse) != i) {
						List<Integer> temp = new ArrayList<>();
						temp.add(map.get(postReverse));
						temp.add(i);
						res.add(temp);
					}
				}
				if(isPalindrome(post)) {
					String preReverse = new StringBuilder(pre).reverse().toString();
					if(map.containsKey(preReverse) && map.get(preReverse) != i && post.length() != 0) {
						List<Integer> temp = new ArrayList<>();
						temp.add(i);
						temp.add(map.get(preReverse));
						res.add(temp);
					}
				}
			}
		}
		return res;
	}
	private boolean isPalindrome(String s) {
		int start = 0, end = s.length() - 1;
		while(start < end) {
			if(s.charAt(start) != s.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
}
