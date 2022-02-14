package trie;

import java.util.ArrayList;
import java.util.List;

/*
Given a set of strings which just has lower case letters and a target string, output all the strings for each the edit distance with the target no greater than k.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example 1:
Given words = `["abc", "abd", "abcd", "adc"]` and target = `"ac"`, k = `1`
Return `["abc", "adc"]`
Input:
["abc", "abd", "abcd", "adc"]
"ac"
1
Output:
["abc","adc"]

Explanation:
"abc" remove "b"
"adc" remove "d"
Example 2:

Input:
["acc","abcd","ade","abbcd"]
"abc"
2
Output:
["acc","abcd","ade","abbcd"]

Explanation:
"acc" turns "c" into "b"
"abcd" remove "d"
"ade" turns "d" into "b" turns "e" into "c"
"abbcd" gets rid of "b" and "d"
*/
public class KEditDistance {
	/**
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */
	int tLen;
    public List<String> kDistance(String[] words, String target, int k) {
        // write your code here
    	Trie trie = new Trie();
    	for(String w : words){
    		trie.insert(w);
    	}
    	List<String> res = new ArrayList<>();
    	tLen = target.length();
    	int[] dp = new int[tLen + 1];
    	// dp[i] means the prefix from root of trie to current node, the edit distance between the prefix and target[:i]
    	for(int i = 0; i <= tLen; i++){
    		dp[i] = i;
    	}
    	dfs(trie.root, res, k, target, dp);
    	return res;
    }
    private void dfs(TrieNode node, List<String> res, int k, String target, int[] dp){
    	if(node.isEnd && dp[tLen] <= k){
    		res.add(node.word);
    	}
    	int[] next = new int[tLen + 1];
    	for(int i = 0; i < node.arr.length; i++){
    		if(node.arr[i] != null){
    			next[0] = dp[0] + 1;
    			for(int j = 1; j <= tLen; j++){
    				if(target.charAt(j - 1) == 'a' + i){
    					next[j] = dp[j - 1];
    				} else {
    					next[j] = Math.min(dp[j - 1], Math.min(next[j - 1], dp[j])) + 1;
    				}
    			}
    			dfs(node.arr[i], res, k, target, next);
    		}
    	}
    }
    
    class TrieNode {
    	TrieNode[] arr;
    	boolean isEnd;
    	String word;

    	public TrieNode() {
    		arr = new TrieNode[26];
    		isEnd = false;
    	}
    }
    
    class Trie {
    	TrieNode root;

    	public Trie() {
    		root = new TrieNode();
    	}

    	public void insert(String word) {
    		TrieNode p = root;
    		int len = word.length();
    		for (int i = 0; i < len; i++) {
    			char c = word.charAt(i);
    			int index = c - 'a';
    			if (p.arr[index] == null) {
    				p.arr[index] = new TrieNode();
    			}
    			p = p.arr[index];
    		}
    		p.isEnd = true;
    		p.word = word;
    	}
    }
}
