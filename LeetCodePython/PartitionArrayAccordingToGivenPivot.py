"""
You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums such that the following conditions are satisfied:

Every element less than pivot appears before every element greater than pivot.
Every element equal to pivot appears in between the elements less than and greater than pivot.
The relative order of the elements less than pivot and the elements greater than pivot is maintained.
More formally, consider every pi, pj where pi is the new position of the ith element and pj is the new position of the jth element. If i < j and both elements are smaller (or larger) than pivot, then pi < pj.
Return nums after the rearrangement.

Example 1:
Input: nums = [9,12,5,10,14,3,10], pivot = 10
Output: [9,5,3,10,10,12,14]
Explanation:
The elements 9, 5, and 3 are less than the pivot so they are on the left side of the array.
The elements 12 and 14 are greater than the pivot so they are on the right side of the array.
The relative ordering of the elements less than and greater than pivot is also maintained. [9, 5, 3] and [12, 14] are the respective orderings.

Example 2:
Input: nums = [-3,4,3,2], pivot = 2
Output: [-3,2,4,3]
Explanation:
The element -3 is less than the pivot so it is on the left side of the array.
The elements 4 and 3 are greater than the pivot so they are on the right side of the array.
The relative ordering of the elements less than and greater than pivot is also maintained. [-3] and [4, 3] are the respective orderings.

Constraints:
1 <= nums.length <= 10^5
-10^6 <= nums[i] <= 10^6
pivot equals to an element of nums.

hints:
1 Could you put the elements smaller than the pivot and greater than the pivot in a separate list as in the sequence that they occur?
2 With the separate lists generated, could you then generate the result?

Analysis:
TC: O(N) optimal answer with one pass
"""
from typing import List


class PartitionArrayAccordingToGivenPivot:
    def pivotArray(self, nums: List[int], pivot: int) -> List[int]:
        n = len(nums)
        res = [0] * n
        less_i, greater_i = 0, n - 1
        for l, r in zip(range(n), range(n - 1, -1, -1)):
            if nums[l] < pivot:
                res[less_i] = nums[l]
                less_i += 1
            if pivot < nums[r]:
                res[greater_i] = nums[r]
                greater_i -= 1
        while less_i <= greater_i:
            res[less_i] = pivot
            less_i += 1
        return res
