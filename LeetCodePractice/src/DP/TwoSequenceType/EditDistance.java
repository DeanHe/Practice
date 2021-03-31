  package DP.TwoSequenceType;
/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example
Given word1 = "mart" and word2 = "karma", return 3.
*/
public class EditDistance {
	/**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        int l1 = word1.length(), l2 = word2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        //init
        for(int i = 1; i <= l1; i++){
            dp[i][0] = i;
        }
        for(int j = 1; j <= l2; j++){
            dp[0][j] = j;
        }
        //function
        for(int i = 0; i < l1; i++){
            for(int j = 0; j < l2; j++){
                if(word1.charAt(i) == word2.charAt(j)){ //no opertation
                    dp[i + 1][j + 1] = dp[i][j];
                } else { //insert; delete; replace
                    int temp = Math.min(dp[i][j + 1], dp[i + 1][j]);
                    dp[i + 1][j + 1] = Math.min(dp[i][j], temp) + 1;
                }
            }
        }
        return dp[l1][l2];
    }
}
