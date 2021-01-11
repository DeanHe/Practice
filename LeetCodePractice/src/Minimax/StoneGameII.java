package Minimax;
/*
Alex and Lee continue their games with piles of stones.
There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
The objective of the game is to end with the most stones.
Alex and Lee take turns, with Alex starting first.  Initially, M = 1.
On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
The game continues until all the stones have been taken.
Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.

Example 1:

Input: piles = [2,7,9,4,4]
Output: 10
Explanation:
If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again.
Alex can get 2 + 4 + 4 = 10 piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left.
In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger.

Constraints:

1 <= piles.length <= 100
1 <= piles[i] <= 10 ^ 4
*/
public class  StoneGameII {
	int len;
	int[] sufSum;
	Integer[][] mem;
	public int stoneGameII(int[] piles) {
        len = piles.length;
        sufSum = new int[len];
        mem = new Integer[len][len];
        sufSum[len - 1] = piles[len - 1];
        for(int i = len - 2; i >= 0; i--){
        	sufSum[i] = sufSum[i + 1] + piles[i];
        }
        return helper(piles, 0, 1);
    }
	// the maximum gain for player on turn i and setting M
	private int helper(int[] piles, int i, int M){
		if(i == len){
			return 0;
		}
		if(2 * M + i >= len){
			return sufSum[i];
		}
		if(mem[i][M] != null){
			return mem[i][M];
		}
		int nextPlayerMin = Integer.MAX_VALUE; //the min value the next player can get
		for(int x = 1; x <= 2 * M; x++){
			nextPlayerMin = Math.min(nextPlayerMin, helper(piles, i + x, Math.max(M, x)));
		}
		return mem[i][M] = sufSum[i] - nextPlayerMin;
	}
}
