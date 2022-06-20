"""
Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example 1:
Input: nums = [1,2,1,2,6,7,5,1], k = 2
Output: [0,3,5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.

Example 2:
Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
Output: [0,2,4]


Constraints:
1 <= nums.length <= 2 * 104
1 <= nums[i] < 216
1 <= k <= floor(nums.length / 3)

analysis:
sliding window
"""
from typing import List


class MaximumSumOf3NonOverlappingSubarrays:
    def maxSumOfThreeSubarrays(self, nums: List[int], k: int) -> List[int]:
        most_seq = 0
        most_two_seq = [0, k]
        most_three_seq = [0, k, 2 * k]

        seq_sum = sum(nums[0: k])
        seq_two_sum = sum(nums[k: 2 * k])
        seq_three_sum = sum(nums[2 * k: 3 * k])

        most_seq_sum = seq_sum
        most_two_seq_sum = seq_sum + seq_two_sum
        most_three_seq_sum = seq_sum + seq_two_sum + seq_three_sum

        # Current window positions
        seq_idx = 1
        seq_two_idx = k + 1
        seq_three_idx = 2 * k + 1

        while seq_three_idx + k <= len(nums):
            seq_sum = seq_sum - nums[seq_idx - 1] + nums[seq_idx + k - 1]
            seq_two_sum = seq_two_sum - nums[seq_two_idx - 1] + nums[seq_two_idx + k - 1]
            seq_three_sum = seq_three_sum - nums[seq_three_idx - 1] + nums[seq_three_idx + k - 1]
            if seq_sum > most_seq_sum:
                most_seq = seq_idx
                most_seq_sum = seq_sum
            if seq_two_sum + most_seq_sum > most_two_seq_sum:
                most_two_seq = [most_seq, seq_two_idx]
                most_two_seq_sum = seq_two_sum + most_seq_sum
            if seq_three_sum + most_two_seq_sum > most_three_seq_sum:
                most_three_seq_sum = seq_three_sum + most_two_seq_sum
                most_three_seq = most_two_seq + [seq_three_idx]
            seq_idx += 1
            seq_two_idx += 1
            seq_three_idx += 1

        return most_three_seq

