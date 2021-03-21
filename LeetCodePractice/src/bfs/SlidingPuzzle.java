package bfs;

import java.util.*;

/*On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Example
Example 1:

Given board = `[[1,2,3],[4,0,5]]`, return `1`.

Explanation: 
Swap the 0 and the 5 in one move.
Example 2：

Given board = `[[1,2,3],[5,4,0]]`, return `-1`.

Explanation: 
No number of moves will make the board solved.
Example 3:

Given board = `[[4,1,2],[5,0,3]]`, return `5`.

Explanation: 
5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Example 4:

Given board = `[[3,2,4],[1,5,0]]`, return `14`.
Notice
board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].*/
public class SlidingPuzzle {
	/**
	 * @param board:
	 *            the given board
	 * @return: the least number of moves required so that the state of the
	 *          board is solved
	 */
	public int slidingPuzzle(int[][] board) {
		// write your code here
		String start = "";
		for (int[] row : board) {
			for (int num : row) {
				start += num;
			}
		}
		String target = "123450";
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.offer(start);
		visited.add(start);
		int[] dirs = { -1, 1, -3, 3 };
		int step = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				if (cur.equals(target)) {
					return step;
				}
				int zeroIdx = cur.indexOf("0");
				for (int k = 0; k < dirs.length; k++) {
					int nextIdx = zeroIdx + dirs[k];
					if (nextIdx < 0 || nextIdx >= cur.length() || (zeroIdx == 2 && nextIdx == 3) || (zeroIdx == 3 && nextIdx == 2)) {
						continue;
					}
					String nb = swap(cur, zeroIdx, nextIdx);
					if (!visited.contains(nb)) {
						visited.add(nb);
						queue.offer(nb);
					}
				}
			}
			step++;
		}
		return -1;
	}

	private String swap(String s, int i, int j) {
		char[] arr = s.toCharArray();
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return String.valueOf(arr);
	}
}
