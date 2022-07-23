"""
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example 1:
Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

Example 2:
Input: nums = [-1]
Output: [0]

Example 3:
Input: nums = [-1,-1]
Output: [0,0]

Constraints:
1 <= nums.length <= 105
-104 <= nums[i] <= 104

analysis:
mergeSort
The smaller numbers on the right of a number are exactly those that jump from its right to its left during a stable sort. So I do mergesort with added tracking of those right-to-left jumps.
"""
from typing import List


class CountOfSmallerNumbersAfterSelf:
    def countSmaller(self, nums: List[int]) -> List[int]:
        res = [0] * len(nums)

        def sort(indexes):
            half = len(indexes) // 2
            if half:
                left, right = sort(indexes[:half]), sort(indexes[half:])
                for i in range(len(indexes) - 1, -1, -1):
                    if not right or left and left[-1][1] > right[-1][1]:
                        res[left[-1][0]] += len(right)
                        indexes[i] = left.pop()
                    else:
                        indexes[i] = right.pop()
            return indexes

        sort(list(enumerate(nums)))
        return res