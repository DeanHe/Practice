package dfs;

/*
In an interview, my interviewer asked me this question:

Develop a function to generate a random maze

This is a quite difficult question to solve in 30min if you don't have solved this question before. On the internet there are many solution but none seems easy. The method should respect this constraint:

it should accept the size of the maze (a square maze NxN)
it should consist of only Walls and Path
I will post the answer with the solution so other people will be able to find this question.
 */

import ReservoirSampling.ShuffleAnArray;

import java.util.Arrays;
import java.util.Random;

public class GenerateRandomMaze {
	int[][] dirs = {{0, 2}, {2, 0}, {0, -2}, {-2, 0}};
	int[] idx = {0, 1, 2, 3};
	ShuffleAnArray shuffleAnArray = new ShuffleAnArray(idx);
	Random rand = new Random(dirs.length - 1);
	
	public void test() {
		char[][] maze = new char[10][10];
		for(char[] arr : maze){
			Arrays.fill(arr, '#');
		}
		int[] src = {1, 1};
		int[] dest = {5, 4};
		setStartAndEnd(maze, src, dest);
		generateMaze(maze, src[0], src[1]);
		maze[src[0]][src[1]] = '^';
		maze[dest[0]][dest[1]] = '?';
		//display maze
		for(int r = 0; r < maze.length; r++){
			for(int c = 0; c < maze[0].length; c++){
				System.out.print(maze[r][c]);
			}
			System.out.println();
		}
	}

	private void setStartAndEnd(char[][] maze, int[] src, int[] dest){
		maze[src[0]][src[1]] = '.';
		maze[dest[0]][dest[1]] = '?';
	}

	private void generateMaze(char[][] maze, int r, int c) {
		for(int i : shuffleAnArray.shuffle()){
			int nb_r = r + dirs[i][0];
			int nb_c = c + dirs[i][1];
			if(isGoodPath(maze, r, c, nb_r, nb_c)){
				maze[nb_r][nb_c] = '.';
				generateMaze(maze, nb_r, nb_c);
			}
		}
	}

	private boolean isGoodPath(char[][] maze, int r, int c, int nb_r, int nb_c) {
		if(!isValidPosition(maze, nb_r, nb_c)){
			return false;
		}
		if(maze[nb_r][nb_c] == '.'){
			return false;
		}
		int mid_r = (r + nb_r) / 2;
		int mid_c = (c + nb_c) / 2;
		maze[mid_r][mid_c] = '.';
		return true;
	}

	private boolean isValidPosition(char[][] maze, int r, int c){
		if(r >= 0 && r < maze.length && c >= 0 && c < maze[0].length){
			return true;
		}
		return false;
	}

}

