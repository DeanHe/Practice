package DP;
/*Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example
Given word1 = "mart" and word2 = "karma", return 3.*/
public class EditDistance {
	/**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        //init
         for(int i = 1; i <= n; i++){
            dp[0][i] = i;
        }
        for(int i = 1; i <= m; i++){
            dp[i][0] = i;
        }
        //function 
        for(int i = 1; i <= m; i++){
        	char c1 = word1.charAt(i - 1);
        	for(int j = 1; j <= n; j++){
        		char c2 = word2.charAt(j - 1);
        		if(c1 != c2){
        			//insert; delete; replace
        			int temp = Math.min(dp[i - 1][j], dp[i][j - 1]);
        			dp[i][j] = Math.min(temp, dp[i - 1][j - 1]) + 1;
        		} else {
        		    //no opertation
        			dp[i][j] = dp[i - 1][j - 1];
        		}
        	}
        }
        return dp[m][n];
    }
}
