"""
Given an array of integers arr of even length n and an integer k.
We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
Return true If you can find a way to do that or false otherwise.

Example 1:
Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
Output: true
Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).

Example 2:
Input: arr = [1,2,3,4,5,6], k = 7
Output: true
Explanation: Pairs are (1,6),(2,5) and(3,4).

Example 3:
Input: arr = [1,2,3,4,5,6], k = 10
Output: false
Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.

Constraints:
arr.length == n
1 <= n <= 10^5
n is even.
-10^9 <= arr[i] <= 10^9
1 <= k <= 10^5

hints:
1 Keep an array of the frequencies of ((x % k) + k) % k for each x in arr.
2 for each i in [0, k - 1] we need to check if freq[i] == freq[k - i]
3 Take care of the case when i == k - i and when i == 0

Analaysis:
TC: O(N)
"""
from typing import List


class Solution:
    def canArrange(self, arr: List[int], k: int) -> bool:
        remainder_cnt = [0] * k
        for n in arr:
            n = (n % k + k) % k
            remainder_cnt[n] += 1
        for i in range(k):
            if remainder_cnt[i] != remainder_cnt[k - i]:
                return False
        return remainder_cnt[0] % 2 == 0