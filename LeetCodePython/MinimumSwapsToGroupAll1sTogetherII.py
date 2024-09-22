"""
A swap is defined as taking two distinct positions in an array and swapping the values in them.

A circular array is defined as an array where we consider the first element and the last element to be adjacent.

Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the array together at any location.

Example 1:
Input: nums = [0,1,0,1,1,0,0]
Output: 1
Explanation: Here are a few of the ways to group all the 1's together:
[0,0,1,1,1,0,0] using 1 swap.
[0,1,1,1,0,0,0] using 1 swap.
[1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
There is no way to group all 1's together with 0 swaps.
Thus, the minimum number of swaps required is 1.

Example 2:
Input: nums = [0,1,1,1,0,0,1,1,0]
Output: 2
Explanation: Here are a few of the ways to group all the 1's together:
[1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
[1,1,1,1,1,0,0,0,0] using 2 swaps.
There is no way to group all 1's together with 0 or 1 swaps.
Thus, the minimum number of swaps required is 2.

Example 3:
Input: nums = [1,1,0,0,1]
Output: 0
Explanation: All the 1's are already grouped together due to the circular property of the array.
Thus, the minimum number of swaps required is 0.


Constraints:
1 <= nums.length <= 10^5
nums[i] is either 0 or 1.

hints:
1 Notice that the number of 1’s to be grouped together is fixed. It is the number of 1's the whole array has.
2 Call this number total. We should then check for every subarray of size total (possibly wrapped around), how many swaps are required to have the subarray be all 1’s.
3 The number of swaps required is the number of 0’s in the subarray.
4 To eliminate the circular property of the array, we can append the original array to itself. Then, we check each subarray of length total.
5 How do we avoid recounting the number of 0’s in the subarray each time? The Sliding Window technique can help.

Analysis:
Sliding Window
grouping together circular ones scenario is same as grouping together zeros for normal case
TC O(N)
"""
from typing import List


class MinimumSwapsToGroupAll1sTogetherII:
    def minSwaps(self, nums: List[int]) -> int:
        ones = zeros = s = cnt = 0
        n = len(nums)
        res = n
        for num in nums:
            if num == 0:
                zeros += 1
            else:
                ones += 1
        # group all ones
        for e in range(n):
            if nums[e] == 1:
                cnt += 1
            while e - s + 1 == ones:
                res = min(res, ones - cnt)
                if nums[s] == 1:
                    cnt -= 1
                s += 1
        # group all zeros
        s = cnt = 0
        for e in range(n):
            if nums[e] == 0:
                cnt += 1
            while e - s + 1 == zeros:
                res = min(res, zeros - cnt)
                if nums[s] == 0:
                    cnt -= 1
                s += 1
        return res

