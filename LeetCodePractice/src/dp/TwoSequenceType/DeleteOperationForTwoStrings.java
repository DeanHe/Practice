package dp.TwoSequenceType;

public class DeleteOperationForTwoStrings {
	public int minDistance(String word1, String word2) {
		// word1 - LCS + word2 - LCS
        return word1.length() + word2.length() - 2 * lcs(word1, word2);
    }
	private int lcs(String word1, String word2){
		// write your code here
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        //mem[i][j] means the LCS length formed by A[:i] and B[:j]
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
	}
}
