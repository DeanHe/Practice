package dfs;
/*
Given a map size of m*n, 1 means space, 0 means obstacle, 9 means the endpoint. You start at (0,0) and return whether you can reach the endpoint.

Example
Example1

Input: 
[
	[1,1,1],
	[1,1,1],
	[1,1,9]
]
Output: true
Example2

Input: 
[
	[1,1,1],
	[1,0,0],
	[1,0,9]
]
Output: false
*/
public class CanReachTheEndpoint {
    int[] dirs = {0, 1, 0, -1, 0};
	/**
     * @param map: the map
     * @return: can you reach the endpoint
     */
    public boolean reachEndpoint(int[][] map) {
        // Write your code here
        return dfs(0, 0, map);
    }
    
    private boolean dfs(int r, int c, int[][] map){
        int rows = map.length;
        int cols = map[0].length;
        if(map[r][c] == 0){
            return false;
        }
        if(map[r][c] == 9){
            return true;
        }
        if(map[r][c] == 1){
            map[r][c] = 0;
            for(int i = 0; i < dirs.length - 1; i++){
                int nb_r = r + dirs[i];
                int nb_c = c + dirs[i + 1];
                if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && map[nb_r][nb_c] != 0){
                    if(dfs(nb_r, nb_c, map)){
                        return true;
                    }
                }
            }
                    
        }
        return false;
    }
}
