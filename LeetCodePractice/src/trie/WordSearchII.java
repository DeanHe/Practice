package trie;

import java.util.*;
/*
Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example 1:


Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Example 2:


Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 10^4
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
 */
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
	int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	public List<String> findWords(char[][] board, String[] words) {
		// write your code here
		List<String> res = new ArrayList<>();
		if (board == null || board.length == 0 || words == null || words.length == 0) {
			return res;
		}
		Trie trie = new Trie();
		for (String s : words) {
			trie.insert(s);
		}
		rows = board.length;
		cols = board[0].length;
		boolean[][] visited = new boolean[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				dfs(board, visited, r, c, trie, "");
			}
		}
		res.addAll(set);
		return res;
	}

	private void dfs(char[][] board, boolean[][] visited, int r, int c, Trie trie, String s) {
		if (r < 0 || r >= rows || c < 0 || c >= cols) {
			return;
		}
		if (visited[r][c]) {
			return;
		}
		s = s + board[r][c];
		if (!trie.startWith(s)) {
			return;
		}
		if (trie.search(s)) {
			set.add(s);
		}
		visited[r][c] = true;
		for (int i = 0; i < dirs.length; i++) {
			dfs(board, visited, r + dirs[i][0], c + dirs[i][1], trie, s);
		}
		visited[r][c] = false;
	}
}

