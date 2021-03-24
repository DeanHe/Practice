package MinMax;
/*
There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row),
then Bob calculates the value of each row which is the sum of the values of all the stones in this row.
Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row.
If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.

The game ends when there is only one stone remaining. Alice's is initially zero.

Return the maximum score that Alice can obtain.



Example 1:

Input: stoneValue = [6,2,3,4,5,5]
Output: 18
Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score becomes 16 (11 + 5).
The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2). The game ends because only one stone is remaining in the row.
Example 2:

Input: stoneValue = [7,7,7,7,7,7,7]
Output: 28
Example 3:

Input: stoneValue = [4]
Output: 0


Constraints:

1 <= stoneValue.length <= 500
1 <= stoneValue[i] <= 10^6
1 <= stoneValue[i] <= 10^6

hint:
We need to try all possible divisions for the current row to get the max score.
As calculating all possible divisions will lead us to calculate some sub-problems more than once, we need to think of dynamic programming.


 */
public class StoneGameV {
    Integer[][] mem;
    int len;
    int[] preSum;
    public int stoneGameV(int[] stoneValue) {
        len = stoneValue.length;
        mem = new Integer[len][len];
        preSum = new int[len + 1];
        for(int i = 0; i < len; i++){
            preSum[i + 1] = preSum[i] + stoneValue[i];
        }
        return dfs(0, len - 1, stoneValue);
    }

    private int dfs(int l, int r, int[] stoneValue) {
        if(l == r){
            return 0;
        }
        if(l + 1 == r){
            return Math.min(stoneValue[l], stoneValue[r]);
        }
        if(mem[l][r] != null){
            return mem[l][r];
        }
        int res = 0;
        for(int i = l; i < r; i++){
            //left: [l, i], right = [i + 1, r]
            int left = preSum[i + 1] - preSum[l];
            int right = preSum[r + 1] - preSum[i + 1];
            if(left > right){
                res = Math.max(res, right + dfs(i + 1, r, stoneValue));
            } else if(left < right){
                res = Math.max(res, left + dfs(l, i, stoneValue));
            } else { // left == right
                res = Math.max(res, left + dfs(l, i, stoneValue));
                res = Math.max(res, right + dfs(i + 1, r, stoneValue));
            }

        }
        return mem[l][r] = res;
    }
}
