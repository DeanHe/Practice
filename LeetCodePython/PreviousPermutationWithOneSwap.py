"""
Given an array of positive integers arr (not necessarily distinct), return the lexicographically largest permutation that is smaller than arr, that can be made with exactly one swap (A swap exchanges the positions of two numbers arr[i] and arr[j]). If it cannot be done, then return the same array.

Example 1:
Input: arr = [3,2,1]
Output: [3,1,2]
Explanation: Swapping 2 and 1.

Example 2:
Input: arr = [1,1,5]
Output: [1,1,5]
Explanation: This is already the smallest permutation.

Example 3:
Input: arr = [1,9,4,6,7]
Output: [1,7,4,6,9]
Explanation: Swapping 9 and 7.


Constraints:
1 <= arr.length <= 10^4
1 <= arr[i] <= 10^4

hints:
1 You need to swap two values, one larger than the other. Where is the larger one located?
"""
from typing import List


class PreviousPermutationWithOneSwap:
    def prevPermOpt1(self, arr: List[int]) -> List[int]:
        n = len(arr)
        if n <= 1:
            return arr
        idx = -1
        for i in range(n - 2, -1, -1):
            if arr[i] > arr[i + 1]:
                idx = i
                break
        if idx == -1:
            return arr
        for i in range(n - 1, idx, -1):
            if arr[idx] > arr[i] != arr[i - 1]:
                arr[i], arr[idx] = arr[idx], arr[i]
                break
        return arr