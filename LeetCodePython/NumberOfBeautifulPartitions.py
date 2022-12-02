"""
You are given a string s that consists of the digits '1' to '9' and two integers k and minLength.

A partition of s is called beautiful if:

s is partitioned into k non-intersecting substrings.
Each substring has a length of at least minLength.
Each substring starts with a prime digit and ends with a non-prime digit. Prime digits are '2', '3', '5', and '7', and the rest of the digits are non-prime.
Return the number of beautiful partitions of s. Since the answer may be very large, return it modulo 10^9 + 7.

A substring is a contiguous sequence of characters within a string.

Example 1:
Input: s = "23542185131", k = 3, minLength = 2
Output: 3
Explanation: There exists three ways to create a beautiful partition:
"2354 | 218 | 5131"
"2354 | 21851 | 31"
"2354218 | 51 | 31"

Example 2:
Input: s = "23542185131", k = 3, minLength = 3
Output: 1
Explanation: There exists one way to create a beautiful partition: "2354 | 218 | 5131".

Example 3:
Input: s = "3312958", k = 3, minLength = 1
Output: 1
Explanation: There exists one way to create a beautiful partition: "331 | 29 | 58".


Constraints:
1 <= k, minLength <= s.length <= 1000
s consists of the digits '1' to '9'.

hint:
1 For every index with composite value and every possible size of a partition, try to count the number of possible beautiful partitions.
2 Try to come up with O(n^2*k) dynamic programming solution.
3 To improve time complexity, one should notice that we can use two pointers to find the closest index with a prime value that is at least minLength away.
4 With prefix sum, we can maintain the sum of every possible beautiful partition before that index.

analysis:
dp(i, at_start, k) means # of ways to split s[i:] to k beautiful partitions with s[i] is at_start(Y/N) of a beautiful partition
TC: O(len(s) * 2 * k)
"""
from functools import lru_cache


class Solution:
    def beautifulPartitions(self, s: str, k: int, minLength: int) -> int:
        n = len(s)
        primes = set('2357')
        MOD = (10 ** 9) + 7

        @lru_cache(None)
        def dfs(i, at_start, k):
            if i == n:
                return 1 if k == 0 else 0
            if i > n or k == 0 or (at_start and s[i] not in primes):
                return 0
            if s[i] in primes:
                if at_start:
                    return dfs(i + minLength - 1, False, k)
                else:
                    return dfs(i + 1, False, k)
            else:
                end_here = dfs(i + 1, True, k - 1)
                not_end_here = dfs(i + 1, False, k)
                return (end_here + not_end_here) % MOD

        return dfs(0, True, k)