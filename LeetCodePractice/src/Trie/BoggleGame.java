package Trie;

import java.util.*;

/*Given a board which is a 2D matrix includes a-z and dictionary dict, find the largest collection of words on the board, the words can not overlap in the same position. return the size of largest collection.

Example
Example 1:

Input:
["abc","def","ghi"]
{"abc","defi","gh"}
Output:
3

Explanation:
we can get the largest collection`["abc", "defi", "gh"]`
Example 2:

Input:
["a","f","gi"]
{"abc","defi","gh"}
Output:
0
Notice
The words in the dictionary are not repeated.
You can reuse the words in the dictionary.*/
public class BoggleGame {
	/*
	 * @param board: a list of lists of character
	 * 
	 * @param words: a list of string
	 * 
	 * @return: an integer
	 */
	int rows, cols;

	public int boggleGame(char[][] board, String[] words) {
		// write your code here
		ArrayList<String> res = new ArrayList<>();
		if (board == null || board.length == 0 || words == null || words.length == 0) {
			return 0;
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
				ArrayList<String> fill = new ArrayList<>();
				dfs(board, visited, r, c, trie, "", fill, res);
			}
		}
		return res.size();
	}

	private void dfs(char[][] board, boolean[][] visited, int r, int c, Trie trie, String s, ArrayList<String> fill,
			ArrayList<String> res) {
		if (fill.size() > res.size()) {
			res.clear();
			res.addAll(fill);
		}
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
		visited[r][c] = true;
		if (trie.search(s)) {
			fill.add(s);
			for (int r1 = 0; r1 < rows; r1++) {
				for (int c1 = 0; c1 < cols; c1++) {
					dfs(board, visited, r1, c1, trie, "", fill, res);
				}
			}
			fill.remove(fill.size() - 1);
		} else {
			int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
			for (int i = 0; i < dirs.length; i++) {
				dfs(board, visited, r + dirs[i][0], c + dirs[i][1], trie, s, fill, res);
			}
		}
		visited[r][c] = false;
	}
}
