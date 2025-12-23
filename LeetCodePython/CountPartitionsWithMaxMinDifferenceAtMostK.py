"""
You are given an integer array nums and an integer k. Your task is to partition nums into one or more non-empty contiguous segments such that in each segment, the difference between its maximum and minimum elements is at most k.

Return the total number of ways to partition nums under this condition.

Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:
Input: nums = [9,4,1,3,7], k = 4
Output: 6
Explanation:
There are 6 valid partitions where the difference between the maximum and minimum elements in each segment is at most k = 4:
[[9], [4], [1], [3], [7]]
[[9], [4], [1], [3, 7]]
[[9], [4], [1, 3], [7]]
[[9], [4, 1], [3], [7]]
[[9], [4, 1], [3, 7]]
[[9], [4, 1, 3], [7]]

Example 2:
Input: nums = [3,3,4], k = 0
Output: 2
Explanation:
There are 2 valid partitions that satisfy the given conditions:
[[3], [3], [4]]
[[3, 3], [4]]

Constraints:
2 <= nums.length <= 5 * 10^4
1 <= nums[i] <= 10^9
0 <= k <= 10^9

hints:
1 Use dynamic programming.
2 Let dp[idx] be the count of ways to partition the array with the last partition ending at index idx.
3 Try using a sliding window; we can track the minimum and maximum in the window using deques.

analysis:
DP + monotonic queue (Two Monotonic Queues inc_q for min and dec_q for max in sliding window[l:r])
acc means the sum of total dp[l..r] range which meets conditions
TC: O(N)
"""
from collections import deque
from typing import List


class CountPartitionsWithMaxMinDifferenceAtMostK:
    def countPartitions(self, nums: List[int], k: int) -> int:
        MOD = 10 ** 9 + 7
        sz = len(nums)
        acc = 1
        dp = [0] * (sz + 1)
        dp[0] = 1
        l = 0
        inc_q = deque()
        dec_q = deque()
        for r in range(sz):
            while inc_q and nums[r] < nums[inc_q[-1]]:
                inc_q.pop()
            inc_q.append(r)
            while dec_q and nums[dec_q[-1]] < nums[r]:
                dec_q.pop()
            dec_q.append(r)
            while k < nums[dec_q[0]] - nums[inc_q[0]]:
                acc = (acc - dp[l]) % MOD
                l += 1
                if dec_q[0] < l:
                    dec_q.popleft()
                if inc_q[0] < l:
                    inc_q.popleft()
            dp[r + 1] = acc
            acc = (acc + dp[r + 1]) % MOD
        return dp[sz]

    def countPartitions2(self, nums: List[int], k: int) -> int:
        MOD = 10 ** 9 + 7
        sz = len(nums)
        dp = [0] * (sz + 1)
        dp[0] = 1
        dp_pre_sum = [0] * (sz + 1)
        dp_pre_sum[0] = 1
        l = 0
        inc_q = deque()
        dec_q = deque()
        for r in range(sz):
            while inc_q and nums[r] < nums[inc_q[-1]]:
                inc_q.pop()
            inc_q.append(r)
            while dec_q and nums[dec_q[-1]] < nums[r]:
                dec_q.pop()
            dec_q.append(r)
            while k < nums[dec_q[0]] - nums[inc_q[0]]:
                l += 1
                if dec_q[0] < l:
                    dec_q.popleft()
                if inc_q[0] < l:
                    inc_q.popleft()
            if l > 0:
                dp[r + 1] = (dp_pre_sum[r] - dp_pre_sum[l - 1] + MOD) % MOD
            else:
                dp[r + 1] = dp_pre_sum[r]
            dp_pre_sum[r + 1] = (dp_pre_sum[r] + dp[r + 1]) % MOD
        return dp[sz]

