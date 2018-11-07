package DP;
/*Given n items with size Ai and value Vi, and a backpack with size m. 
What's the maximum value can you put into the backpack?
Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.*/
public class Backpack {
	/**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        int len = A.length;
        boolean[][] dp = new boolean[len + 1][m + 1];
        //initialize
        for(int i = 0; i <= len; i++){
            dp[i][0] = true;
        }
        //func
        for(int i = 1; i <= len; i++){
            for(int j = 1; j <= m; j++){
                dp[i][j] = dp[i - 1][j];
                if(j >= A[i-1] && dp[i - 1][j - A[i - 1]]){
                    dp[i][j] = true;
                } 
            }
        }
        for(int i = m; i >= 0; i--){
            if(dp[len][i]){
                return i;
            }
        }
        return 0;       
    }
}
