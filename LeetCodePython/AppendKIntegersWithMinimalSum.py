"""
You are given an integer array nums and an integer k. Append k unique positive integers that do not appear in nums to nums such that the resulting total sum is minimum.

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
"""
class AppendKIntegersWithMinimalSum(object):
    def minimalKSum(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        nums = sorted(list(set(nums)))
        n = len(nums)

        if nums[n - 1] <= k + n:
            return (k + n) * (k + n + 1) // 2 - sum(nums)

        s, e = 0, n - 1
        while s < e:
            mid = (s + e) // 2
            if nums[mid] - mid <= k:
                s = mid + 1;
            else:
                e = mid
        return (k + s) * (k + s + 1) // 2 - sum(nums[:s])