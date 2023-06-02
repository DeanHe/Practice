"""
You are given a 0-indexed array nums comprising of n non-negative integers.

In one operation, you must:

Choose an integer i such that 1 <= i < n and nums[i] > 0.
Decrease nums[i] by 1.
Increase nums[i - 1] by 1.
Return the minimum possible value of the maximum integer of nums after performing any number of operations.

Example 1:
Input: nums = [3,7,1,6]
Output: 5
Explanation:
One set of optimal operations is as follows:
1. Choose i = 1, and nums becomes [4,6,1,6].
2. Choose i = 3, and nums becomes [4,6,2,5].
3. Choose i = 1, and nums becomes [5,5,2,5].
The maximum integer of nums is 5. It can be shown that the maximum number cannot be less than 5.
Therefore, we return 5.

Example 2:
Input: nums = [10,1]
Output: 10
Explanation:
It is optimal to leave nums as is, and since 10 is the maximum value, we return 10.

Constraints:
n == nums.length
2 <= n <= 10^5
0 <= nums[i] <= 10^9

hints:
1 Try a binary search approach.
2 Perform a binary search over the minimum value that can be achieved for the maximum number of the array.
3 In each binary search iteration, iterate through the array backwards, greedily decreasing the current element until it is within the limit.

analysis:
In one operation:
decrease A[i] by 1.
increase A[i - 1] by 1.

We actualy move the value of A[i] to A[i - 1] by 1,
the sum won't change.

If A[i] < A[i + 1],
then we can repeatly do the operations,
until A[i] >= A[i+1].
So finally the array A will become decrescent order.

We calculate the prefix sum array and their average.
The average is the lower bound of the result,
and it's reachable lower bound by the process in intuition,
so this average is the result.

TC: O(N)
"""
from typing import List


class MinimizeMaximumOfArray:
    def minimizeArrayValue(self, nums: List[int]) -> int:
        res = total = 0
        for i, n in enumerate(nums):
            total += n
            res = max(res, (total + i) // (i + 1))
        return res