package Contest;

import java.util.*;

/*
In an n*n grid, there is a snake that spans 2 cells and starts moving from the top left corner at (0, 0) and (0, 1). The grid has empty cells represented by zeros and blocked cells represented by ones. The snake wants to reach the lower right corner at (n-1, n-2) and (n-1, n-1).

In one move the snake can:

Move one cell to the right if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
Move down one cell if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
Rotate clockwise if it's in a horizontal position and the two cells under it are both empty. In that case the snake moves from (r, c) and (r, c+1) to (r, c) and (r+1, c).

Rotate counterclockwise if it's in a vertical position and the two cells to its right are both empty. In that case the snake moves from (r, c) and (r+1, c) to (r, c) and (r, c+1).

Return the minimum number of moves to reach the target.

If there is no way to reach the target, return -1.

		Example 1:
		Input: grid = [[0,0,0,0,0,1],
		               [1,1,0,0,1,0],
		               [0,0,0,0,1,1],
		               [0,0,1,0,1,0],
		               [0,1,1,0,0,0],
		               [0,1,1,0,0,0]]
		Output: 11
		Explanation:
		One possible solution is [right, right, rotate clockwise, right, down, down, down, down, rotate counterclockwise, right, down].
		
		Example 2:

		Input: grid = [[0,0,1,1,1,1],
		               [0,0,0,0,1,1],
		               [1,1,0,0,0,1],
		               [1,1,1,0,0,1],
		               [1,1,1,0,0,1],
		               [1,1,1,0,0,0]]
		Output: 9
*/

public class MinimumMovesToReachTargetWithRotations {
	public int minimumMoves(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        // state[0] is snake tail position r; state[1] is snake tail position c; state[2] is dir 0 for horizontal, 1 for vertical; state[3] is steps taken
        int[] start = {0, 0, 0, 0}, target = {rows - 1, cols - 2, 0};
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        while(!queue.isEmpty()) {
        	int[] cur = queue.poll();
        	int r = cur[0], c = cur[1], dir = cur[2], steps = cur[3];
        	if(Arrays.equals(Arrays.copyOf(cur, 3), target)) {
        		return steps;
        	}
        	String hash = r + ":" + c + ":" + dir;
        	if(!visited.contains(hash)) {
        		visited.add(hash);
        		if(dir == 0) {  // horizontal
        			if(c + 2 < cols && grid[r][c + 2] == 0) { // move right
        				queue.offer(new int[] {r, c + 1, 0, steps + 1});
        			}
        			if(r + 1 < rows && grid[r + 1][c] + grid[r + 1][c + 1] == 0) { // rotate clockwise or move down
        				queue.offer(new int[] {r, c, 1, steps + 1});
        				queue.offer(new int[] {r + 1, c, 0, steps + 1});
        			}  			
        		} else {  // vertical
        			if(r + 2 < rows && grid[r + 2][c] == 0) { // move down
        				queue.offer(new int[] {r + 1, c, 1, steps + 1});
        			}
        			if(c + 1 < cols && grid[r][c + 1] + grid[r + 1][c + 1] == 0) { // rotate counterclockwise or move right
        				queue.offer(new int[] {r, c, 0, steps + 1});
        				queue.offer(new int[] {r, c + 1, 1, steps + 1});
        			}
        		}
        	}
        }
        return -1;
    }
}
