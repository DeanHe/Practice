package dp;
/*
Given n balloons, indexed from 0 to n-1.
Each balloon is painted with a number on it represented by array nums.
You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

analysis:
dp, divide and conquer, reverse step
mem[l][r] means the max coins get from values[l:r
pay attention to the 1*8*1 step above, which corresponds to values[l] * values[i] * values[r]
 */
public class BurstBalloons {
    Integer[][] mem;
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] values = new int[len + 2];
        for(int i = 0; i < len; i++){
            values[i + 1] = nums[i];
        }
        values[0] = values[len + 1] = 1;
        mem = new Integer[len + 2][len + 2];
        return dfs(values, 0, len + 1);
    }

    private int dfs(int[] values, int l, int r){
        if(l + 1 == r){
            return 0;
        }
        if(mem[l][r] != null){
            return mem[l][r];
        }
        int res = Integer.MIN_VALUE;
        for(int i = l + 1; i <= r - 1; i++){
            res = Math.max(res,
                    dfs(values, l, i) + values[l] * values[i] * values[r] + dfs(values, i, r));
        }
        return mem[l][r] = res;
    }
}
