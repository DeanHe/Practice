package Trie;

import java.util.*;

public class WordSquaresWithTrie {
	int len;
	public List<List<String>> wordSquares(String[] words) {
		List<List<String>> res = new ArrayList<>();
		if(words == null || words.length == 0){
			return res;
		}
		len = words[0].length();
		Trie trie = new Trie(words);
		List<String> square = new ArrayList<>();
		for(String word : words){
			square.add(word);
			dfs(trie, square, res);
			square.remove(square.size() - 1);
		}
		return res;
	}
	private void dfs(Trie trie, List<String> square, List<List<String>> res){
		if(square.size() == len){
			res.add(new ArrayList<>(square));
			return;
		}
		int row = square.size();
		String prefix = "";
		for(String word : square){
			prefix += word.charAt(row);
		}
		List<String> candidates = trie.findByPrefix(prefix);
		for(String cand : candidates){
			square.add(cand);
			dfs(trie, square, res);
			square.remove(square.size() - 1);
		}
	}
	
	class TrieNode {
		List<String> startWith;
		TrieNode[] children;
		public TrieNode() {
			startWith = new ArrayList<>();
			children = new TrieNode[26];
		}
	}
	
	class Trie {
		TrieNode root;
		public Trie(String[] words){
			root = new TrieNode();
			for(String word : words){
				TrieNode cur = root;
				char[] arr = word.toCharArray();
				for(char c : arr){
					int idx = c - 'a';
					if(cur.children[idx] == null){
						cur.children[idx] = new TrieNode();
					}
					cur.children[idx].startWith.add(word);
					cur = cur.children[idx];
				}
			}
		}
		public List<String> findByPrefix(String prefix){
			List<String> res = new ArrayList<>();
			TrieNode cur = root;
			char[] arr = prefix.toCharArray();
			for(char c : arr){
				int idx = c - 'a';
				if(cur.children[idx] == null){
					return res;
				}
				cur = cur.children[idx];
			}
			res.addAll(cur.startWith);
			return res;
		}
	}
}
