package dp.memorization;
/*
#1770

You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.

You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:

Choose one integer x from either the start or the end of the array nums.
Add multipliers[i] * x to your score.
Remove x from the array nums.
Return the maximum score after performing m operations.



Example 1:

Input: nums = [1,2,3], multipliers = [3,2,1]
Output: 14
Explanation: An optimal solution is as follows:
- Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
- Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
- Choose from the end, [1], adding 1 * 1 = 1 to the score.
The total score is 9 + 4 + 1 = 14.
Example 2:

Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
Output: 102
Explanation: An optimal solution is as follows:
- Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
- Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
- Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
- Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
- Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
The total score is 50 + 15 - 9 + 4 + 42 = 102.


Constraints:

n == nums.length
m == multipliers.length
1 <= m <= 10^3
m <= n <= 10^5
-1000 <= nums[i], multipliers[i] <= 1000

 */
public class MaximumScoreFromPerformingMultiplicationOperations {
    Integer[][] mem;
    public int maximumScore(int[] nums, int[] multipliers) {
        mem = new Integer[multipliers.length][multipliers.length];
        return dfs(nums, multipliers, 0, nums.length - 1, 0);
    }

    private int dfs(int[] nums, int[] multipliers, int s, int e, int i) {
        if(i == multipliers.length){
            return 0;
        }
        if(mem[s][nums.length - 1 - e] != null){
            return mem[s][nums.length - 1 - e];
        }
        int a1 = nums[s] * multipliers[i] + dfs(nums, multipliers, s + 1, e, i + 1);
        int a2 = nums[e] * multipliers[i] + dfs(nums, multipliers, s, e - 1, i + 1);
        return mem[s][nums.length - 1 - e] = Math.max(a1, a2);
    }
}
