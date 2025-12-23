"""
You are given an integer array nums.

A subarray is called balanced if the number of distinct even numbers in the subarray is equal to the number of distinct odd numbers.

Return the length of the longest balanced subarray.

Example 1:
Input: nums = [2,5,4,3]
Output: 4

Explanation:
The longest balanced subarray is [2, 5, 4, 3].
It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [5, 3]. Thus, the answer is 4.

Example 2:
Input: nums = [3,2,2,5,4]
Output: 5

Explanation:
The longest balanced subarray is [3, 2, 2, 5, 4].
It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [3, 5]. Thus, the answer is 5.

Example 3:
Input: nums = [1,2,3,2]

Output: 3
Explanation:
The longest balanced subarray is [2, 3, 2].
It has 1 distinct even number [2] and 1 distinct odd number [3]. Thus, the answer is 3.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5

hints:
1 Store the first (or all) occurrences for each value in pos[val].
2 Build a lazy segment tree over start indices l in [0..n-1] that supports range add and can tell if any index has value 0 (keep mn/mx).
3 Use sign = +1 for odd values and sign = -1 for even values.
4 Initialize by adding each value's contribution with update(p, n-1, sign) where p is its current first occurrence.
5 Slide left l: pop pos[nums[l]], let next = next occurrence or n, do update(0, next-1, -sign), then query for any r >= l with value 0 and update ans = max(ans, r-l+1).

analysis:
segment tree:
TC: O(NlogN)
"""
from typing import List


class SegmentTree:
    def __init__(self, n: int):
        self.n = n
        self.min_tree = [0] * (4 * n)
        self.max_tree = [0] * (4 * n)
        self.lazy = [0] * (4 * n)

    def update(self, idx: int, start: int, end: int):
        if self.lazy[idx] != 0:
            self.min_tree[idx] += self.lazy[idx]
            self.max_tree[idx] += self.lazy[idx]
            if start != end:
                self.lazy[2 * idx] += self.lazy[idx]
                self.lazy[2 * idx + 1] += self.lazy[idx]
            self.lazy[idx] = 0

    def update_range(self, idx: int, start: int, end: int, l: int, r: int, val: int):
        # lazy load previous val
        self.update(idx, start, end)
        if start > end or start > r or end < l:
            return
        if l <= start and end <= r:
            self.lazy[idx] += val
            self.update(idx, start, end)
            return

        mid = (start + end) // 2
        self.update_range(2 * idx, start, mid, l, r, val)
        self.update_range(2 * idx + 1, mid + 1, end, l, r, val)
        self.min_tree[idx] = min(self.min_tree[2 * idx], self.min_tree[2 * idx + 1])
        self.max_tree[idx] = max(self.max_tree[2 * idx], self.max_tree[2 * idx + 1])

    def find_leftmost_zero(self, idx: int, start: int, end: int):
        # lazy load previous val
        self.update(idx, start, end)
        if self.min_tree[idx] > 0 or self.max_tree[idx] < 0:
            return -1
        if start == end:
            return start if self.min_tree[idx] == 0 else -1
        mid = (start + end) // 2
        left = self.find_leftmost_zero(2 * idx, start, mid)
        if left != -1:
            return left
        return self.find_leftmost_zero(2 * idx + 1, mid + 1, end)


class LongestBalancedSubarrayII:
    def longestBalanced(self, nums: List[int]) -> int:
        n = len(nums)
        pre_idx = {0: -1}
        res = 0
        st = SegmentTree(n)
        for r in range(n):
            num = nums[r]
            if num % 2 == 0:
                val = 1
            else:
                val = -1
            if num in pre_idx:
                st.update_range(1, 0, n - 1, 0, pre_idx[num], -val)
            st.update_range(1, 0, n - 1, 0, r, val)
            pre_idx[num] = r
            l = st.find_leftmost_zero(1, 0, n - 1)
            if l != -1 and l <= r:
                res = max(res, r - l + 1)
        return res
