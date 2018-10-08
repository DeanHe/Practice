package DP;
//Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
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
