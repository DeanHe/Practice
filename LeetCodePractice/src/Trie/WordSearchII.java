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
		int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int i = 0; i < dirs.length; i++) {
			dfs(board, visited, r + dirs[i][0], c + dirs[i][1], trie, s);
		}
		visited[r][c] = false;
	}
}

