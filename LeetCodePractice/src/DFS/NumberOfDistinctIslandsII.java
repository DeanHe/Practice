package DFS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

Example
Example 1:

11000
10000
00001
00011
Given the above grid map, return 1.

Notice that:

11
1
and

 1
11
are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.

Example 2:

11100
10001
01001
01110
Given the above grid map, return 2.

Here are the two distinct islands:

111
1
and

1
1
Notice that:

111
1
and

1
111
are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.

Notice
The length of each dimension in the given grid does not exceed 50.
*/
public class NumberOfDistinctIslandsII {
    int[] dirs = {0, -1, 0, 1, 0};
    int[][] trans = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    Set<String> set = new HashSet<>();
    int rows, cols;
	/**
     * @param grid: the 2D grid
     * @return: the number of distinct islands
     */
    public int numDistinctIslands2(int[][] grid) {
        // write your code here
        rows = grid.length;
        cols = grid[0].length;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 1){
                    List<int[]> ls = new ArrayList<>();
                    dfs(grid, r, c, ls);
                    set.add(normalize(ls));
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int r, int c, List<int[]> ls) {
        grid[r][c] = 0;
        ls.add(new int[]{r, c});
        for(int i = 0; i < dirs.length - 1; i++){
            int nb_r = r + dirs[i];
            int nb_c = c + dirs[i + 1];
            if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && grid[nb_r][nb_c] == 1){
                dfs(grid, nb_r, nb_c, ls);
            }
        }
    }

    private String normalize(List<int[]> island){
        List<String> sameIslandSet = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            List<int[]> flip = new ArrayList<>();
            List<int[]> rotate = new ArrayList<>();
            for(int[] p : island){
                int r = p[0];
                int c = p[1];
                flip.add(new int[]{r * trans[i][0], c * trans[i][1]});
                rotate.add(new int[]{c * trans[i][0], r * trans[i][1]});
            }
            sameIslandSet.add(getStr(flip));
            sameIslandSet.add(getStr(rotate));
        }
        Collections.sort(sameIslandSet);
        return sameIslandSet.get(0);
    }

    private String getStr(List<int[]> island){
        Collections.sort(island, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] -b[0];
            }
            return a[1] - b[1];
        });
        StringBuilder sb = new StringBuilder();
        int start_r = island.get(0)[0];
        int start_c =island.get(0)[1];
        for(int[] p : island){
            sb.append((p[0] - start_r) + " " + (p[1] - start_c));
        }
        return sb.toString();
    }
}
