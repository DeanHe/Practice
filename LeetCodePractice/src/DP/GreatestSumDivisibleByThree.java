package DP;

import java.util.Arrays;

/*
Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.

        Example 1:

        Input: nums = [3,6,5,1,8]
        Output: 18
        Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
        Example 2:

        Input: nums = [4]
        Output: 0
        Explanation: Since 4 is not divisible by 3, do not pick any number.
        Example 3:

        Input: nums = [1,2,3,4,4]
        Output: 12
        Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).


        Constraints:

        1 <= nums.length <= 4 * 10^4
        1 <= nums[i] <= 10^4

        !A number is a multiple of three if and only if its sum of digits is a multiple of three.
*/
public class GreatestSumDivisibleByThree {
    public int maxSumDivThree(int[] nums) {
        int sum = 0, leftOne = 20000, leftTwo = 20000;
        for(int n : nums){
            sum += n;
            if(n % 3 == 1) {
                leftTwo = Math.min(leftTwo, leftOne + n);
                leftOne = Math.min(leftOne, n);
            } else if(n % 3 == 2){
                leftOne = Math.min(leftOne, leftTwo + n);
                leftTwo = Math.min(leftTwo, n);
            }
        }
        if(sum % 3 == 0) {
            return sum;
        } else if(sum % 3 == 2){
            return sum - leftTwo;
        } else {
            return sum - leftOne;
        }
    }

    public int maxSumDivThreeDP(int[] nums) {
        return maxSumDivK1(nums, 3);
    }

    private int maxSumDivK1(int[] nums, int k){
        if(k == 0){
            return -1;
        }
        int len = nums.length;
        int[][] dp = new int[len + 1][k];
        for(int i = 1; i <= len; i++){
            int n = nums[i - 1];
            for(int j = 0; j < k; j++){
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                dp[i][(n + dp[i - 1][j]) % k] = Math.max(dp[i][(n + dp[i - 1][j]) % k], n + dp[i - 1][j]);
            }
            //System.out.println(Arrays.toString(dp[i]));
        }
        return dp[len][0];
    }

    public int maxSumDivK2(int[] nums, int k){
        if(k==0) return -1;
        int[] dp = new int[k];
        for(int num : nums){
            int tmp[] = Arrays.copyOf(dp,k);
            for(int i=0;i<k;i++){
                dp[(num+tmp[i])%k] = Math.max(dp[(num+tmp[i])%k],num+tmp[i]);
            }
            //System.out.println(Arrays.toString(dp));
        }
        return dp[0];
    }
}
