package dp;

/*
A sequence is special if it consists of a positive number of 0s, followed by a positive number of 1s, then a positive number of 2s.

For example, [0,1,2] and [0,0,1,1,1,2] are special.
In contrast, [2,1,0], [1], and [0,1,2,0] are not special.
Given an array nums (consisting of only integers 0, 1, and 2), return the number of different subsequences that are special. Since the answer may be very large, return it modulo 109 + 7.

A subsequence of an array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements. Two subsequences are different if the set of indices chosen are different.



Example 1:

Input: nums = [0,1,2,2]
Output: 3
Explanation: The special subsequences are [0,1,2,2], [0,1,2,2], and [0,1,2,2].
Example 2:

Input: nums = [2,2,0,0]
Output: 0
Explanation: There are no special subsequences in [2,2,0,0].
Example 3:

Input: nums = [0,1,2,0,1,2]
Output: 7
Explanation: The special subsequences are:
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]
- [0,1,2,0,1,2]


Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 2

hint:
Can we first solve a simpler problem? Counting the number of subsequences with 1s followed by 0s.
How can we keep track of the partially matched subsequences to help us find the answer?

analysis:
dp[i][0] means the number of special sequence ended with 0 in nums[:i].
dp[i][1] means the number of special sequence ended with 0,1 in nums[:i].
dp[i][2] means the number of special sequence ended with,1,2 in nums[:i].
 */
public class CountNumberOfSpecialSubsequences {
    int MOD = (int) (1e9 + 7);

    public int countSpecialSubsequences(int[] nums) {
        int len = nums.length;
        long[][] dp = new long[len + 1][3];
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                dp[i + 1][0] = (2 * dp[i][0]) % MOD + 1;
                dp[i + 1][1] = dp[i][1];
                dp[i + 1][2] = dp[i][2];
            } else if (nums[i] == 1) {
                dp[i + 1][0] = dp[i][0];
                dp[i + 1][1] = (dp[i][0] + (2 * dp[i][1]) % MOD) % MOD;
                dp[i + 1][2] = dp[i][2];
            } else {
                dp[i + 1][0] = dp[i][0];
                dp[i + 1][1] = dp[i][1];
                dp[i + 1][2] = (dp[i][1] + (2 * dp[i][2]) % MOD) % MOD;
            }
            //System.out.println(Arrays.toString(dp[i + 1]));
        }
        return (int) (dp[len][2] % MOD);
    }
}

