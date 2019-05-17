package DFS;
/*A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.
The world is modeled as a 2-D array of cells, where 0 represents uninfected cells, and 1 represents cells contaminated with the virus. A wall (and only one wall) can be installed between any two 4-directionally adjacent cells, on the shared boundary.
Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited. Each day, you can install walls around only one region -- the affected area (continuous block of infected cells) that threatens the most uninfected cells the following night. There will never be a tie.
Can you save the day? If so, what is the number of walls required? If not, and the world becomes fully infected, return the number of walls used.

Example 1:
Input: grid = 
[[0,1,0,0,0,0,0,1],
 [0,1,0,0,0,0,0,1],
 [0,0,0,0,0,0,0,1],
 [0,0,0,0,0,0,0,0]]
Output: 10
Explanation:
There are 2 contaminated regions.
On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:

[[0,1,0,0,0,0,1,1],
 [0,1,0,0,0,0,1,1],
 [0,0,0,0,0,0,1,1],
 [0,0,0,0,0,0,0,1]]

On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.
Example 2:
Input: grid = 
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output: 4
Explanation: Even though there is only one cell saved, there are 4 walls built.
Notice that walls are only built on the shared boundary of two different cells.
Example 3:
Input: grid = 
[[1,1,1,0,0,0,0,0,0],
 [1,0,1,0,1,1,1,1,1],
 [1,1,1,0,0,0,0,0,0]]
Output: 13
Explanation: The region on the left only builds two new walls.
Note:
The number of rows and columns of grid will each be in the range [1, 50].
Each grid[i][j] will be either 0 or 1.
Throughout the described process, there is always a contiguous viral region that will infect strictly more uncontaminated squares in the next round.
*/

import java.util.*;

public class ContainVirus {
	Set<Integer> visited;
	List<Set<Integer>> affectedRegions;
	List<Set<Integer>> frontiers;
	List<Integer> walls;
	int[][] grid;
	int rows, cols;
	int[] dirs = {0, 1, 0, -1, 0};
	public int containVirus(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        int res = 0;
        //continuous simulation
        while(true){
        	visited = new HashSet<>();
        	affectedRegions = new ArrayList<>();
        	frontiers = new ArrayList<>();
        	walls = new ArrayList<>();
        	for(int r = 0; r < rows; r++){
        		for(int c = 0; c < cols; c++){
        			int idx = r * cols + c;
        			if(grid[r][c] == 1 && !visited.contains(idx)){
        				affectedRegions.add(new HashSet<>());
        				frontiers.add(new HashSet<>());
        				walls.add(0);
        				dfs(r, c);
        			}
        		}
        	}
        	if(affectedRegions.isEmpty()){
        		break;
        	}
        	int mostAffectRegionIdx = 0;
        	for(int i = 0; i < frontiers.size(); i++){
        		if(frontiers.get(mostAffectRegionIdx).size() < frontiers.get(i).size()){
        			mostAffectRegionIdx = i;
        		}
        	}
        	res += walls.get(mostAffectRegionIdx);
        	// reset controlled affected region and update other affected region
        	for(int i = 0; i < affectedRegions.size(); i++){
        		Set<Integer> region = affectedRegions.get(i);
        		if(i == mostAffectRegionIdx){
        			for(int idx : region){
        				int r = idx / cols;
        				int c = idx % cols;
        				grid[r][c] = -1;
        			}
        		} else {
        			for(int idx : region){
        				int r = idx / cols;
        				int c = idx % cols;
        				for(int j = 0; j + 1 < dirs.length; j++){
        					int nb_r = r + dirs[j];
        					int nb_c = c + dirs[j + 1];
        					if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && grid[nb_r][nb_c] == 0){
        						grid[nb_r][nb_c] = 1;
        					}
        				}
        			}
        		}
        	}
        }
        return res;
    }
	private void dfs(int r, int c){
		int idx = r * cols + c;
		visited.add(idx);
		int last = affectedRegions.size() - 1;
		affectedRegions.get(last).add(idx);
		for(int i = 0; i + 1 < dirs.length; i++){
			int nb_r = r + dirs[i];
			int nb_c = c + dirs[i + 1];
			if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols){
				int nb_idx = nb_r * cols + nb_c;
				if(grid[nb_r][nb_c] == 1 && !visited.contains(nb_idx)){
					dfs(nb_r, nb_c);
				} else if(grid[nb_r][nb_c] == 0){
					frontiers.get(last).add(nb_idx);
					walls.set(last, walls.get(last) + 1);
				}
			}
		}
	}
}
