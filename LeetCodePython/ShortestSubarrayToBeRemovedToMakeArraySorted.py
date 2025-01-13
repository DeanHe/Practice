"""
Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.

Return the length of the shortest subarray to remove.

A subarray is a contiguous subsequence of the array.

Example 1:
Input: arr = [1,2,3,10,4,2,3,5]
Output: 3
Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
Another correct solution is to remove the subarray [3,10,4].

Example 2:
Input: arr = [5,4,3,2,1]
Output: 4
Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].

Example 3:
Input: arr = [1,2,3]
Output: 0
Explanation: The array is already non-decreasing. We do not need to remove any elements.

Constraints:
1 <= arr.length <= 10^5
0 <= arr[i] <= 10^9

hints:
1 The key is to find the longest non-decreasing subarray starting with the first element or ending with the last element, respectively.
2 After removing some subarray, the result is the concatenation of a sorted prefix and a sorted suffix, where the last element of the prefix is smaller than the first element of the suffix.

Analysis:
TC: O(N)
"""
from typing import List


class ShortestSubarrayToBeRemovedToMakeArraySorted:
    def findLengthOfShortestSubarray(self, arr: List[int]) -> int:
        sz = len(arr)
        s, e = 0, sz - 1
        while e > 0 and arr[e - 1] <= arr[e]:
            e -= 1
        res = e
        while s < e and (s == 0 or arr[s - 1] <= arr[s]):
            while e < sz and arr[e] < arr[s]:
                e += 1
            res = min(res, e - s - 1)
            s += 1
        return res
