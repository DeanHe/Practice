package Palindrome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

Example 2:
Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]

Constraints:

1 <= words.length <= 5000
0 <= words[i].length <= 300
words[i] consists of lower-case English letters.

analysis:
approach 1: trie TC O(words.length * (avg word length) ^ 2)
trie saves the reverse string of words
ref:http://www.allenlipeng47.com/blog/index.php/2016/03/15/palindrome-pairs/
*/
public class PalindromePairs {
	class TrieNode {
		TrieNode[] children;
		int idx;
		List<Integer> palins;

		public TrieNode() {
			children = new TrieNode[26];
			idx = -1;
			palins = new ArrayList<>();
		}
	}

	List<List<Integer>> res;
	TrieNode root;
	String[] words;
	public List<List<Integer>> palindromePairs(String[] words) {
		res = new ArrayList<>();
		root = new TrieNode();
		this.words = words;
		for(int i = 0; i < words.length; i++){
			add(i);
		}
		for(int i = 0; i < words.length; i++){
			search(i);
		}
		return res;
	}

	private void add(int idx){
		String word = words[idx];
		TrieNode cur = root;
		//reverse loop
		for(int i = word.length() - 1; i >= 0; i--){
			int j = word.charAt(i) - 'a';
			if(cur.children[j] == null){
				cur.children[j] = new TrieNode();
			}
			if(isPalindrome(word, 0, i)){
				cur.palins.add(idx);
			}
			cur = cur.children[j];
		}
		cur.palins.add(idx);
		cur.idx = idx;
	}

	private void search(int idx){
		String word = words[idx];
		TrieNode cur = root;
		for(int i = 0; i < word.length(); i++){
			if(cur.idx >= 0 && cur.idx != idx && isPalindrome(word, i, word.length() - 1)){
				res.add(Arrays.asList(idx, cur.idx));
			}
			cur = cur.children[word.charAt(i) - 'a'];
			if(cur == null){
				return;
			}
		}
		for(int i : cur.palins){
			if(i != idx){
				res.add(Arrays.asList(idx, i));
			}
		}
	}

	private boolean isPalindrome(String word, int s, int e) {
		while(s < e){
			if(word.charAt(s) != word.charAt(e)){
				return false;
			}
			s++;
			e--;
		}
		return true;
	}

	//////////////// appraoch 2 ///////////////////
	public List<List<Integer>> palindromePairsII(String[] words) {
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
						res.add(Arrays.asList(map.get(postReverse), i));
					}
				}
				if(isPalindrome(post)) {
					String preReverse = new StringBuilder(pre).reverse().toString();
					if(map.containsKey(preReverse) && map.get(preReverse) != i && post.length() != 0) {
						res.add(Arrays.asList(i, map.get(preReverse)));
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
