package math;

import java.util.Arrays;

/*
You are given an integer array nums and an integer k.
Append k unique positive integers that do not appear in nums to nums such that the resulting total sum is minimum.
Return the sum of the k integers appended to nums.

Example 1:
Input: nums = [1,4,25,10,25], k = 2
Output: 5
Explanation: The two unique positive integers that do not appear in nums which we append are 2 and 3.
The resulting sum of nums is 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70, which is the minimum.
The sum of the two integers appended is 2 + 3 = 5, so we return 5.

Example 2:
Input: nums = [5,6], k = 6
Output: 25
Explanation: The six unique positive integers that do not appear in nums which we append are 1, 2, 3, 4, 7, and 8.
The resulting sum of nums is 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36, which is the minimum.
The sum of the six integers appended is 1 + 2 + 3 + 4 + 7 + 8 = 25, so we return 25.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i], k <= 10^9

hint:
1 The k smallest numbers that do not appear in nums will result in the minimum sum.
2 Recall that the sum of the first n positive numbers is equal to n * (n+1) / 2.
3 Initialize the answer as the sum of 1 to k. Then, adjust the answer depending on the values in nums.

TC: NlogN
 */
public class AppendKIntegersWithMinimalSum {
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long res = 0;
        int i = 0, pre = 0;
        while (k > 0 && i < nums.length) {
            int cnt = nums[i] - pre - 1;
            if (cnt > k) {
                cnt = k;
            }
            if (cnt > 0) {
                long start = pre;
                long end = start + cnt;
                res += end * (1 + end) / 2 - start * (1 + start) / 2;
                k -= cnt;
            }
            pre = nums[i];
            i++;
        }
        if (k > 0) {
            long start = pre;
            long end = start + k;
            res += end * (1 + end) / 2 - start * (1 + start) / 2;
        }
        return res;
    }
}
