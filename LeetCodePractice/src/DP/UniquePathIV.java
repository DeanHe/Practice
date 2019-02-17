package DP;
/*Give two integers to represent the height and width of the grid. 
 * The starting point is in the upper left corner and the ending point is in the upper right corner. 
 * You can go to the top right, right or bottom right. 
 * Find out the number of paths you can reach the end. And the result needs to mod 1000000007.*/
public class UniquePathIV {
	/**
     * @param height: the given height
     * @param width: the given width
     * @return: the number of paths you can reach the end
     */
    public int uniquePath(int height, int width) {
        // Write your code here
        long[][] dp = new long[height][width];
        dp[0][0] = 1;
        for(int c = 1; c < width; c++){
            for(int r = 0; r < height; r++){
                dp[r][c] = dp[r][c - 1];
                if(r > 0){
                    dp[r][c] += dp[r - 1][c - 1];
                }
                if(r + 1 < height){
                    dp[r][c] += dp[r + 1][c - 1];
                }
                dp[r][c] = dp[r][c] % 1000000007;
            }
        }
        return (int)dp[0][width - 1];
    }
}
