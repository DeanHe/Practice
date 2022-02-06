package MinMax;

import java.util.Arrays;

/*
Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones from the first remaining stones in the row.
The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.
The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.
Assume Alice and Bob play optimally.
Return the maximum amount "Alice" can get.
*/
public class StoneGameIIIGoogle {
    int len;
    int[] stoneVal, mem;
    int[] preSum;
    public int stoneGameIII(int[] stoneValue) {
        len = stoneValue.length;
        stoneVal = stoneValue;
        mem = new int[len];
        preSum = new int[1 + len];
        for(int i = 0; i < len; i++){
            preSum[i + 1] = preSum[i] + stoneValue[i];
        }
        Arrays.fill(mem, Integer.MIN_VALUE);
        int score = dfs(0);
        return score;
    }
    // dfs means the maximum relative score one can get start at index i of stones
    private int dfs(int i){
        if(i >= len){
            return 0;
        }
        if(mem[i] != Integer.MIN_VALUE){
            return mem[i];
        }
        for(int j = 0; j < 3 && i + j < len; j++){
            mem[i] = Math.max(mem[i], preSum[len] - preSum[i] - dfs(i + j + 1));
        }
        return mem[i];
    }
}
