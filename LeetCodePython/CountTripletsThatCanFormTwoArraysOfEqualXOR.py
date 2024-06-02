"""
Given an array of integers arr.

We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).

Let's define a and b as follows:

a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
Note that ^ denotes the bitwise-xor operation.

Return the number of triplets (i, j and k) Where a == b.

Example 1:
Input: arr = [2,3,1,6,7]
Output: 4
Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)

Example 2:
Input: arr = [1,1,1,1,1]
Output: 10

Constraints:
1 <= arr.length <= 300
1 <= arr[i] <= 10^8

hints:
1 We are searching for sub-array of length ≥ 2 and we need to split it to 2 non-empty arrays so that the xor of the first array is equal to the xor of the second array. This is equivalent to searching for sub-array with xor = 0.
2 Keep the prefix xor of arr in another array, check the xor of all sub-arrays in O(n^2), if the xor of sub-array of length x is 0 add x-1 to the answer.

analysis:
prefix sum
TC: O(N)
"""
from collections import defaultdict
from typing import List


class CountTripletsThatCanFormTwoArraysOfEqualXOR:
    def countTriplets(self, arr: List[int]) -> int:
        res = 0
        sz = len(arr)
        pre_sum = [0] * (sz + 1)
        for i in range(sz):
            pre_sum[i + 1] = pre_sum[i] ^ arr[i]
        cnt = defaultdict(int)
        total_idx = defaultdict(int)
        for i, xor in enumerate(pre_sum):
            res += (cnt[xor] * (i - 1) - total_idx[xor])
            total_idx[xor] += i
            cnt[xor] += 1
        return res


