package DP;
/*In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.
Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are {10,0001,1,0}
Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
*/
/*This problem is a typical 0-1 knapsack problem, we need to pick several strings in provided strings to get the maximum number of strings using limited number 0 and 1. We can create a three dimensional array, in which mem[i][j][k] means the maximum number of strings we can get from the first i argument strs using limited j number of '0's and k number of '1's.
For mem[i][j][k], we can get it by fetching the current string i or discarding the current string, which would result in mem[i][j][k] = mem[i-1][j-numOfZero(strs[i])][i-numOfOnes(strs[i])] and mem[i][j][k] = mem[i-1][j][k]; We only need to treat the larger one in it as the largest number for mem[i][j][k].*/
public class OnesAndZeroes {
	public int findMaxForm(String[] strs, int m, int n) {
        int size = strs.length;
        int[][][] dp = new int[size + 1][m + 1][n + 1];
        for(int i = 0; i <= size; i++){
        	int[] cnt = {0, 0};
        	if(i > 0){
        		String num = strs[i - 1];
        		cnt = countOneAndZeros(num);
        	}
        	for(int j = 0; j <= m; j++){
        		for(int k = 0; k <= n; k++){
        			if(i > 0){
        				dp[i][j][k] = dp[i - 1][j][k];
        				if(j >= cnt[0] && k >= cnt[1]){
        					dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - cnt[0]][k - cnt[1]] + 1);
        				}
        			}
        		}
        	}
        }
        return dp[size][m][n];
    }
	private int[] countOneAndZeros(String s){
		int[] res = {0, 0};
		char[] arr = s.toCharArray();
		for(char c : arr){
			if(c == '0'){
				res[0]++;
			} else if(c == '1'){
				res[1]++;
			}
		}
		return res;
	}
}
