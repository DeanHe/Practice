package DFS;

import java.util.Arrays;

public class GenerateRandomMaze {
	
	private boolean[][] maze, northWall, southWall, westWall, eastWall;
	private int destination_r, destination_c;
	
	public void main() {
		destination_r = 8;
		destination_c = 8;
		maze(10);
		for(boolean[] arr : maze){
			Arrays.fill(arr, false);
		}
		boolean canSolve = solve(0, 0);
		System.out.println(canSolve);
	}
	
	public boolean[][] maze(int n) {
		maze = new boolean[n][n];
		northWall = new boolean[n][n];
		southWall = new boolean[n][n];
		westWall = new boolean[n][n];
		eastWall = new boolean[n][n];
		// initialze all walls as present
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				northWall[r][c] = true;
				southWall[r][c] = true;
				westWall[r][c] = true;
				eastWall[r][c] = true;
			}
		}
		generate(0, 0);
		return maze;
	}
	private void generate(int r, int c) {
		// true means path/unblock, for wall true means exist, block
		maze[r][c] = true;
		if (r == destination_r && c == destination_c) {
			return;
		}
		// while there is an unvisited block neighbor
		while (isValidBlock(r + 1, c) 
				|| isValidBlock(r - 1, c)
				|| isValidBlock(r, c + 1)
				|| isValidBlock(r, c - 1)) {
			// pick random neighbor
			while (true) {
				int rand = (int)(Math.random() * 3);
				if (rand == 0 && isValidBlock(r + 1, c)) {
					//break wall
					southWall[r][c] = false;
					northWall[r + 1][c] = false;
					generate(r + 1, c);
					break;
				} else if (rand == 1 && isValidBlock(r - 1, c)) {
					northWall[r][c] = false;
					southWall[r - 1][c] = false;
					generate(r - 1, c);
					break;
				} else if (rand == 2 && isValidBlock(r, c + 1)) {
					eastWall[r][c] = false;
					westWall[r][c + 1] = false;
					generate(r, c + 1);
					break;
				} else if (rand == 3 && isValidBlock(r, c - 1)) {
					westWall[r][c] = false;
					eastWall[r][c - 1] = false;
					generate(r, c + 1);
					break;
				}
			}
			
		}
		
	}
	private boolean isValidBlock(int r, int c) {
		return r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && !maze[r][c];
	}
	private boolean solve(int r, int c) {
		if(maze[r][c]){
			// visited
			return false;
		}
		maze[r][c] = true;
		if(r == destination_r && c == destination_c){
			return true;
		}
		boolean res = false;
		if(!northWall[r][c]){
			res |= solve(r - 1, c);
		}
		if(!southWall[r][c]){
			res |= solve(r + 1, c);
		}
		if(!eastWall[r][c]){
			res |= solve(r, c + 1);
		}
		if(!westWall[r][c]){
			res |= solve(r, c - 1);
		}
		return res;
	}
}
