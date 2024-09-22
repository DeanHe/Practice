"""
You are given the array nums consisting of n positive integers. You computed the sum of all non-empty continuous subarrays from the array and then sorted them in non-decreasing order, creating a new array of n * (n + 1) / 2 numbers.

Return the sum of the numbers from index left to index right (indexed from 1), inclusive, in the new array. Since the answer can be a huge number return it modulo 109 + 7.

Example 1:
Input: nums = [1,2,3,4], n = 4, left = 1, right = 5
Output: 13
Explanation: All subarray sums are 1, 3, 6, 10, 2, 5, 9, 3, 7, 4. After sorting them in non-decreasing order we have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 1 to ri = 5 is 1 + 2 + 3 + 3 + 4 = 13.

Example 2:
Input: nums = [1,2,3,4], n = 4, left = 3, right = 4
Output: 6
Explanation: The given array is the same as example 1. We have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 3 to ri = 4 is 3 + 3 = 6.

Example 3:
Input: nums = [1,2,3,4], n = 4, left = 1, right = 10
Output: 50

Constraints:
n == nums.length
1 <= nums.length <= 1000
1 <= nums[i] <= 100
1 <= left <= right <= n * (n + 1) / 2

hints:
1 Compute all sums and save it in array.
2 Then just go from LEFT to RIGHT index and calculate answer modulo 1e9 + 7.

analysis:
Sliding window + binary search

Let n be the size and Sum be the total sum of the nums array.
TC: O(n * logSum)

The total size of the search space is O(Sum). Therefore, time complexity for binary search is O(logSum).
Inside each binary search operation, the countAndSum function takes O(n) time.
"""
from typing import List


class Solution:
    def rangeSum(self, nums: List[int], n: int, left: int, right: int) -> int:
        MOD = 10 ** 9 + 7

        # the total sum of all sub arrays from nums[:k]
        def first_k_sum(k):

            # count of sub arrays with sum less target
            # the total sum of all those sub arrays
            def count_and_sum(target):
                cnt = cur_sum = total_sum = window_sum = 0
                l = 0
                for r in range(n):
                    cur_sum += nums[r]
                    window_sum += nums[r] * (r - l + 1)
                    while cur_sum > target:
                        window_sum -= cur_sum
                        cur_sum -= nums[l]
                        l += 1
                    cnt += r - l + 1
                    total_sum += window_sum
                return cnt, total_sum

            s = min(nums)
            e = sum(nums)
            while s <= e:
                mid = (s + e) // 2
                cnt, _ = count_and_sum(mid)
                if k <= cnt:
                    e = mid - 1
                else:
                    s = mid + 1
            cnt, total_sum = count_and_sum(s)
            # There can be more sub arrays with the same sum of left.
            return total_sum - s * (cnt - k)

        res = (first_k_sum(right) - first_k_sum(left - 1)) % MOD
        return (res + MOD) % MOD



