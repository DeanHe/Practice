package DP;
/*There are G people in a gang, and a list of various crimes they could commit.

The i-th crime generates a profit[i] and requires group[i] gang members to participate.

If a gang member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least P profit, and the total number of gang members participating in that subset of crimes is at most G.

How many schemes can be chosen?  Since the answer may be very large, return it modulo 10^9 + 7.

 

Example 1:

Input: G = 5, P = 3, group = [2,2], profit = [2,3]
Output: 2
Explanation: 
To make a profit of at least 3, the gang could either commit crimes 0 and 1, or just crime 1.
In total, there are 2 schemes.
Example 2:

Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8]
Output: 7
Explanation: 
To make a profit of at least 5, the gang could commit any crimes, as long as they commit one.
There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).*/

/*
 * state: (current profit, people used)
 */
public class ProfitableSchemes {
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
    	int K = profit.length; // # of crimes
    	//mem[k][i][j] # of schemes to achieve at least i profit using j people by committing first k crimes
        int[][][] dp = new int[K + 1][P + 1][G + 1];
        dp[0][0][0] = 1;
        for(int k = 1; k <= K; k++){
        	int p = profit[k - 1];
        	int g = group[k - 1];
        	for(int i = 0; i <= P; i++){
        		for(int j = 0; j <= G; j++){
        			dp[k][i][j] = dp[k - 1][i][j];
        			if(j >= g){
        				if(i >= p){
        					dp[k][i][j] += dp[k - 1][i - p][j - g];
        				} else {
        					dp[k][i][j] += dp[k - 1][0][j - g];
        				}
        			}
        		}
        	}
        }
        int res = 0;
        for(int x : dp[K][P]){
        	res += x;
        }
        return res;
    }
    
    public int profitableSchemesMod(int G, int P, int[] group, int[] profit) {
    	int mod = (int) (1e9 + 7);
    	int K = profit.length; // # of crimes
    	//mem[k][i][j] # of schemes to achieve at least i profit using j people by committing first k crimes
        int[][][] dp = new int[K + 1][P + 1][G + 1];
        dp[0][0][0] = 1;
        for(int k = 1; k <= K; k++){
        	int p = profit[k - 1];
        	int g = group[k - 1];
        	for(int i = 0; i <= P; i++){
        		for(int j = 0; j <= G; j++){
        			dp[k][i][j] = dp[k - 1][i][j];
        			if(j >= g){
        				if(i >= p){
        					dp[k][i][j] = (dp[k][i][j] + dp[k - 1][i - p][j - g]) % mod;
        				} else {
        					dp[k][i][j] = (dp[k][i][j] + dp[k - 1][0][j - g]) % mod;
        				}
        			}
        		}
        	}
        }
        int res = 0;
        for(int x : dp[K][P]){
        	res = (res + x) % mod;
        }
        return res;
    }
}
