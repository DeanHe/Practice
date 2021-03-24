package DP.bitmask;

/*
You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.

In the ith operation (1-indexed), you will:

Choose two elements, x and y.
Receive a score of i * gcd(x, y).
Remove x and y from nums.
Return the maximum score you can receive after performing n operations.

The function gcd(x, y) is the greatest common divisor of x and y.



Example 1:

Input: nums = [1,2]
Output: 1
Explanation: The optimal choice of operations is:
(1 * gcd(1, 2)) = 1
Example 2:

Input: nums = [3,4,6,8]
Output: 11
Explanation: The optimal choice of operations is:
(1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
Example 3:

Input: nums = [1,2,3,4,5,6]
Output: 14
Explanation: The optimal choice of operations is:
(1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14


Constraints:

1 <= n <= 7
nums.length == 2 * n
1 <= nums[i] <= 10^6

analysis:
TC O(n^2 * 2^n)
SC O(n * 2^n)
 */
public class MaximizeScoreAfterNOperations {
    int len;
    public int maxScore(int[] nums) {
        len = nums.length;
        int[][] mem = new int[len / 2 + 1][1 << len];
        return dfs(mem, nums, 1, 0);
    }

    private int dfs(int[][] mem, int[] nums, int op, int state) {
        if(op > len / 2){
            return 0;
        }
        if(mem[op][state] != 0){
            return mem[op][state];
        }
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                int pick = (1 << i) + (1 << j);
                if((state & pick) == 0){
                    mem[op][state] = Math.max(mem[op][state],
                            op * gcd(nums[i], nums[j]) + dfs(mem, nums, op + 1, state + pick));
                }
            }
        }
        return mem[op][state];
    }

    private int gcd(int a, int b){
        if(a == 0){
            return b;
        }
        return gcd(b % a, a);
    }
}
