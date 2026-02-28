"""
You are given a binary string s, and an integer k.

In one operation, you must choose exactly k different indices and flip each '0' to '1' and each '1' to '0'.

Return the minimum number of operations required to make all characters in the string equal to '1'. If it is not possible, return -1.

Example 1:
Input: s = "110", k = 1
Output: 1
Explanation:
There is one '0' in s.
Since k = 1, we can flip it directly in one operation.

Example 2:
Input: s = "0101", k = 3
Output: 2

Explanation:
One optimal set of operations choosing k = 3 indices in each operation is:

Operation 1: Flip indices [0, 1, 3]. s changes from "0101" to "1000".
Operation 2: Flip indices [1, 2, 3]. s changes from "1000" to "1111".
Thus, the minimum number of operations is 2.

Example 3:
Input: s = "101", k = 2
Output: -1
Explanation:
Since k = 2 and s has only one '0', it is impossible to flip exactly k indices to make all '1'. Hence, the answer is -1.

Constraints:
1 <= s.length <= 10^5
s[i] is either '0' or '1'.
1 <= k <= s.length

hints:
1 Model state as z = number of zeros; flipping k picks i zeros (i between max(0, k - (n - z)) and min(k, z)) and transforms z to z' = z + k - 2 * i, so z' lies in a contiguous range and has parity (z + k) % 2.
2 Build a graph on states 0..n and run BFS from initial z to reach 0; each edge from z goes to all z' in that computed interval.
3 For speed, keep two ordered sets of unvisited states by parity and erase ranges with lower_bound while BFSing to achieve near O(NlogN) time.
"""
import math


class MinimumOperationsToEqualizeBinaryString:
    def minOperations(self, s: str, k: int) -> int:
        size = len(s)
        zeros = s.count('0')
        if size == k:
            if  zeros == 0:
                return 0
            if zeros == size:
                return 1
            else:
                return -1
        res = math.inf
        if zeros % 2 == 0:
            ops = max(self.ceiling(zeros, k), self.ceiling(zeros, size - k))
            ops += ops & 1
            res = min(res, ops)
        if zeros % 2 == k % 2:
            ops = max(self.ceiling(zeros, k), self.ceiling(size - zeros, size - k))
            if ops & 1 == 0:
                ops += 1
            res = min(res, ops)
        return res if res < math.inf else -1

    def ceiling(self, a, b):
        return (a + b - 1) // b
