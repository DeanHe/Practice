package BFS;
/*On a 3x3 board, there are 8 tiles represented by the integers 1 through 8, and an empty square represented by 0.
A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
Given an initial state of the puzzle board and final state, return the least number of moves required so that the initial state to final state.
If it is impossible to move from initial state to final state, return -1.

Example
Example 1:

Input:
[
 [2,8,3],
 [1,0,4],
 [7,6,5]
]
[
 [1,2,3],
 [8,0,4],
 [7,6,5]
]
Output:
4

Explanation:
[                 [
 [2,8,3],          [2,0,3],
 [1,0,4],   -->    [1,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [2,0,3],          [0,2,3],
 [1,8,4],   -->    [1,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [0,2,3],          [1,2,3],
 [1,8,4],   -->    [0,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [1,2,3],          [1,2,3],
 [0,8,4],   -->    [8,0,4],
 [7,6,5]           [7,6,5]
]                 ]
Example 2：

Input:
[[2,3,8],[7,0,5],[1,6,4]]
[[1,2,3],[8,0,4],[7,6,5]]
Output:
-1
Challenge
How to optimize the memory?
Can you solve it with A* algorithm?*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzleII {
	/**
	 * @param init_state:
	 *            the initial state of chessboard
	 * @param final_state:
	 *            the final state of chessboard
	 * @return: return an integer, denote the number of minimum moving
	 */
	int rows, cols;
	int[] dirs = { 0, 1, 0, -1, 0 };

	public int minMoveStep(int[][] init_state, int[][] final_state) {
		rows = init_state.length;
		cols = init_state[0].length;
		String start = matrixToString(init_state);
		String target = matrixToString(final_state);
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.offer(start);
		visited.add(start);
		int move = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				if (cur.equals(target)) {
					return move;
				}
				List<String> nbs = getNeighbors(cur);
				for (String nb : nbs) {
					if (!visited.contains(nb)) {
						queue.offer(nb);
						visited.add(nb);
					}
				}
			}
			move++;
		}
		return -1;
	}

	private String matrixToString(int[][] matrix) {
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				sb.append(matrix[r][c]);
			}
		}
		return sb.toString();
	}

	private List<String> getNeighbors(String s) {
		List<String> res = new ArrayList<>();
		int zeroIdx = s.indexOf("0");
		int zeroRow = zeroIdx / cols;
		int zeroCol = zeroIdx % cols;
		for (int i = 0; i < dirs.length - 1; i++) {
			int nbRow = zeroRow + dirs[i];
			int nbCol = zeroCol + dirs[i + 1];
			if (nbRow < 0 || nbRow >= rows || nbCol < 0 || nbCol >= cols) {
				continue;
			}
			int nbIdx = nbRow * cols + nbCol;
			res.add(swap(s, zeroIdx, nbIdx));
		}
		return res;
	}

	private String swap(String s, int i, int j) {
		char[] arr = s.toCharArray();
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return String.valueOf(arr);
	}
}
