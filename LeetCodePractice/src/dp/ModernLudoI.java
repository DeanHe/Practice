package dp;

// https://www.lintcode.com/problem/modern-ludo-i/description

public class ModernLudoI {
	/**
     * @param length: the length of board
     * @param connections: the connections of the positions
     * @return: the minimum steps to reach the end
     */
    public int modernLudo(int length, int[][] connections) {
        // Write your code here
        int[] dp = new int[length + 1];
        // initialization
        int index = 2;
        while(index <= length && index <= 7){
            dp[index] = 1;
            index++;
        }
        //transformation 
        while(index <= length){
            dp[index] = Integer.MAX_VALUE;
            for(int i = index - 1; i >= index - 6; i--){
                dp[index] = Math.min(dp[index], dp[i] + 1);
            }
            for(int[] connect : connections){
                int from = connect[0];
                int to = connect[1];
                if(to == index){
                    dp[index] = Math.min(dp[index], dp[from]);
                }
            }
            index++;
        }
        return dp[length];
    }
}
