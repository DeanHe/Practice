package BFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*Given a 3 x 3 matrix, the number is 1~9, among which 8 squares have numbers, 1~8, and one is null (indicated by 0), asking if the corresponding number can be put on the corresponding label In the grid (spaces can only be swapped with up, down, left, and right positions), if it can output "YES", otherwise it outputs "NO".

Example
Example 1:

Given matrix =[[1,2,3],[4,0,6],[7,5,8]]
return `"YES"`。

Explanation:
First exchange 5 with a space, then 8 with a space exchange.
Example 2:

Given matrix =[[1,2,3],[4,5,6],[7,0,8]]
return `"YES"`。

Explanation:
Just swap 8 with the space.*/
public class SlidingPuzzleIII {
	 /**
     * @param matrix: The 3*3 matrix
     * @return: The answer
     */
	int rows, cols;
	int[] dirs = { 0, 1, 0, -1, 0 };

	public String jigsawPuzzle(int[][] matrix) {
		rows = matrix.length;
		cols = matrix[0].length;
		String start = matrixToString(matrix);
		String target = "123456780";
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.offer(start);
		visited.add(start);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				if (cur.equals(target)) {
					return "YES";
				}
				List<String> nbs = getNeighbors(cur);
				for (String nb : nbs) {
					if (!visited.contains(nb)) {
						queue.offer(nb);
						visited.add(nb);
					}
				}
			}
		}
		return "NO";
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
