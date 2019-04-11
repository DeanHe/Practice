package Trie;

import java.util.*;
//Given a matrix of lower alphabets and a dictionary. 
//Find all words in the dictionary that can be found in the matrix. A word can start from any position in the matrix and go left/right/up/down to the adjacent position. 
//One character only be used once in one word.
public class WordSearchII {
	/**
	 * @param board:
	 *            A list of lists of character
	 * @param words:
	 *            A list of string
	 * @return: A list of string
	 */
	HashSet<String> set = new HashSet<>();
	int rows, cols;
	public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
		// write your code here
		ArrayList<String> res = new ArrayList<>();
		if (board == null || board.length == 0 || words == null || words.size() == 0) {
			return res;
		}
		Trie trie = new Trie();
		for (String s : words) {
			trie.insert(s);
		}
		rows = board.length;
		cols = board[0].length;
		boolean[][] checked = new boolean[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				dfs(board, checked, r, c, trie, "");
			}
		}
		res.addAll(set);
		return res;
	}

	private void dfs(char[][] board, boolean[][] checked, int r, int c, Trie trie, String s) {
		if (r < 0 || r >= rows || c < 0 || c >= cols) {
			return;
		}
		if (checked[r][c]) {
			return;
		}
		s = s + board[r][c];
		if (!trie.startWith(s)) {
			return;
		}
		if (trie.search(s) == true) {
			set.add(s);
		}
		checked[r][c] = true;
		dfs(board, checked, r + 1, c, trie, s);
		dfs(board, checked, r - 1, c, trie, s);
		dfs(board, checked, r, c + 1, trie, s);
		dfs(board, checked, r, c - 1, trie, s);
		checked[r][c] = false;
	}
}

class TrieNode {
	TrieNode[] arr;
	boolean isEnd;

	public TrieNode() {
		arr = new TrieNode[26];
	}
}

class Trie {
	TrieNode root;
	public Trie(){
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
	}

	public boolean search(String word) {
		TrieNode p = root;
		int len = word.length();
		for (int i = 0; i < len; i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (p.arr[index] == null) {
				return false;
			}
			p = p.arr[index];
		}
		if (p == root) {
			return false;
		}
		return p.isEnd;
	}

	public boolean startWith(String word) {
		TrieNode p = root;
		int len = word.length();
		for (int i = 0; i < len; i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (p.arr[index] == null) {
				return false;
			}
			p = p.arr[index];
		}
		if (p == root) {
			return false;
		}
		return p != null;
	}
}
