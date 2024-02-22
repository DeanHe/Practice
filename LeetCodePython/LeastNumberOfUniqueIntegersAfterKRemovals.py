"""
Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.

Example 1:
Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.
Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.

Constraints:
1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
0 <= k <= arr.length

hints:
1 Use a map to count the frequencies of the numbers in the array.
2 An optimal strategy is to remove the numbers with the smallest count first.
"""
import heapq
from collections import Counter
from typing import List


class Solution:
    def findLeastNumOfUniqueInts(self, arr: List[int], k: int) -> int:
        cnt = Counter(arr)
        frequencies = list(cnt.values())
        heapq.heapify(frequencies)
        removed = 0
        while frequencies:
            removed += heapq.heappop(frequencies)
            if k < removed:
                return len(frequencies) + 1
        return 0