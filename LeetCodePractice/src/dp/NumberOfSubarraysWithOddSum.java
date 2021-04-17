package dp;
/*
Given an array of integers arr. Return the number of sub-arrays with odd sum.

As the answer may grow large, the answer must be computed modulo 10^9 + 7.



Example 1:

Input: arr = [1,3,5]
Output: 4
Explanation: All sub-arrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
All sub-arrays sum are [1,4,9,3,8,5].
Odd sums are [1,9,3,5] so the answer is 4.
Example 2:

Input: arr = [2,4,6]
Output: 0
Explanation: All sub-arrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
All sub-arrays sum are [2,6,12,4,10,6].
All sub-arrays have even sum and the answer is 0.
Example 3:

Input: arr = [1,2,3,4,5,6,7]
Output: 16
Example 4:

Input: arr = [100,100,99,99]
Output: 4
Example 5:

Input: arr = [7]
Output: 1


Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 100

hint:
Can we use the accumulative sum to keep track of all the odd-sum sub-arrays ?
if the current accu sum is odd, we care only about previous even accu sums and vice versa.
 */
public class NumberOfSubarraysWithOddSum {
    public int numOfSubarrays(int[] arr) {
        int MOD = (int)(1e9 + 7);
        int len = arr.length;
        long res = 0;
        // dp[i][0] means # of subarray end with arr[i] has even sum; dp[i][1] means # of subarray end with arr[i] has odd sum
        long[][] dp = new long[len + 1][2];
        for(int i = 1; i <= len; i++){
            if(arr[i - 1] % 2 == 1){
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1];
            }
            res += dp[i][1];
        }
        return (int)(res % MOD);
    }
}
