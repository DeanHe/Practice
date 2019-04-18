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

class GenerateRandomMaze2 {
    public int[][] maze(int n) {
        // Assumptions: n = 2 * k + 1, where k > = 0.
        int[][] maze = new int[n][n];
        // initialize the matrix as only (0,0) is corridor,
        // other cells are all walls at the beginning.
        // later we are trying to break the walls to form corridors. 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    maze[i][j] = 0;
                } else {
                    maze[i][j] = 1;
                }
            }
        }
        generate(maze, 0, 0);
        return maze;
    }
    private void generate(int[][] maze, int x, int y) {
        // get a random shuffle of all the possible directions,
        // and follow the shuffled order to do DFS & backtrack. 
        Dir[] dirs = Dir.values();
        shuffle(dirs);
        for (Dir dir: dirs) {
            // advance by two steps.
            int nextX = dir.moveX(x, 2);
            int nextY = dir.moveY(y, 2);
            if (isValidWall(maze, nextX, nextY)) {
                // only if the cell is a wall(meaning we have not visited before), 
                // we break the walls through to make it corridors. 
                maze[dir.moveX(x, 1)][dir.moveY(y, 1)] = 0;
                maze[nextX][nextY] = 0;
                generate(maze, nextX, nextY);
            }
        }
    }
    // Get a random order of the directions.
    private void shuffle(Dir[] dirs) {
        for (int i = 0; i < dirs.length; i++) {
            int index = (int)(Math.random() * (dirs.length - i));
            Dir tmp = dirs[i];
            dirs[i] = dirs[i + index];
            dirs[i + index] = tmp;
        }
    }
    // check if the position (x,y) is within the maze and it is a wall.
    private boolean isValidWall(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] ==
            1;
    }
    enum Dir {
        NORTH(-1, 0), SOUTH(1, 0), EAST(0, -1), WEST(0, 1);
        int deltaX;
        int deltaY;
        Dir(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
        // move certain times of deltax.
        public int moveX(int x, int times) {
            return x + times * deltaX;
        }
        // move certain times of deltaY.
        public int moveY(int y, int times) {
            return y + times * deltaY;

        }
    }
}

