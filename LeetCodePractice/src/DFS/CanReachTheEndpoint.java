// https://www.lintcode.com/problem/can-reach-the-endpoint/description

public class CanReachTheEndpoint {
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
        if(c < 0 || c >= cols || r < 0 || r >= rows){
            return false;
        }
        if(map[r][c] == 0){
            return false;
        }
        if(map[r][c] == 9){
            return true;
        }
        if(map[r][c] == 1){
            map[r][c] = 0;
            return dfs(r + 1, c, map)
                    || dfs(r - 1, c, map)
                    || dfs(r, c + 1, map)
                    || dfs(r, c - 1, map);
                    
        }
        return false;
    }
}
