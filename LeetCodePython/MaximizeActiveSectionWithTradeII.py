"""
You are given a binary string s of length n, where:

'1' represents an active section.
'0' represents an inactive section.
You can perform at most one trade to maximize the number of active sections in s. In a trade, you:

Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.
Additionally, you are given a 2D array queries, where queries[i] = [li, ri] represents a substring s[li...ri].

For each query, determine the maximum possible number of active sections in s after making the optimal trade on the substring s[li...ri].

Return an array answer, where answer[i] is the result for queries[i].

Note
For each query, treat s[li...ri] as if it is augmented with a '1' at both ends, forming t = '1' + s[li...ri] + '1'. The augmented '1's do not contribute to the final count.
The queries are independent of each other.

Example 1:
Input: s = "01", queries = [[0,1]]
Output: [1]

Explanation:
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.

Example 2:
Input: s = "0100", queries = [[0,3],[0,2],[1,3],[2,3]]
Output: [4,3,1,1]

Explanation:
Query [0, 3] → Substring "0100" → Augmented to "101001"
Choose "0100", convert "0100" → "0000" → "1111".
The final string without augmentation is "1111". The maximum number of active sections is 4.

Query [0, 2] → Substring "010" → Augmented to "10101"
Choose "010", convert "010" → "000" → "111".
The final string without augmentation is "1110". The maximum number of active sections is 3.

Query [1, 3] → Substring "100" → Augmented to "11001"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.

Query [2, 3] → Substring "00" → Augmented to "1001"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.

Example 3:
Input: s = "1000100", queries = [[1,5],[0,6],[0,4]]
Output: [6,7,2]

Explanation:
Query [1, 5] → Substring "00010" → Augmented to "1000101"
Choose "00010", convert "00010" → "00000" → "11111".
The final string without augmentation is "1111110". The maximum number of active sections is 6.

Query [0, 6] → Substring "1000100" → Augmented to "110001001"
Choose "000100", convert "000100" → "000000" → "111111".
The final string without augmentation is "1111111". The maximum number of active sections is 7.

Query [0, 4] → Substring "10001" → Augmented to "1100011"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 2.

Example 4:
Input: s = "01010", queries = [[0,3],[1,4],[1,3]]
Output: [4,4,2]

Explanation:
Query [0, 3] → Substring "0101" → Augmented to "101011"
Choose "010", convert "010" → "000" → "111".
The final string without augmentation is "11110". The maximum number of active sections is 4.

Query [1, 4] → Substring "1010" → Augmented to "110101"
Choose "010", convert "010" → "000" → "111".
The final string without augmentation is "01111". The maximum number of active sections is 4.

Query [1, 3] → Substring "101" → Augmented to "11011"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 2.

Constraints:
1 <= n == s.length <= 10^5
1 <= queries.length <= 10^5
s[i] is either '0' or '1'.
queries[i] = [li, ri]
0 <= li <= ri < n

hints:
1 Split consecutive zeros and ones into segments and give each segment an ID.
2 The answer should be the maximum of ans[i] = len[i - 1] + len[i + 1], where i is a one-segment.
3 For a zero-segment, define ans[i] = 0.
4 Note that all three segments (i - 1, i, and i + 1) should be fully covered by the substring.
5 Use a segment tree to perform range maximum queries on the answer. The query to the segment tree is not straightforward since we need to ensure the zero-segments are fully covered. Handle the first and last segments separately.
"""
from bisect import bisect_left, bisect_right
from typing import List

class SegmentTree:
    def __init__(self, arr):
        self.n = len(arr)
        self.arr = arr
        # multiplying that number by 2^2=4
        self.seg = [0] * (self.n << 2)
        if self.n:
            self.build(1, 0, self.n - 1)

    def build(self, i:int, l:int, r:int) -> None:
        if l == r:
            self.seg[i] = self.arr[l]
            return
        mid = (l + r) >> 1
        self.build(i, l, mid)
        self.build(i << 1 | 1, mid + 1, r)
        self.seg[i] = max(self.seg[i << 1], self.seg[i << 1 | 1])

    def query(self, L:int, R:int) -> int:
        if L > R:
            return 0
        def _query(i:int, l:int, r:int) -> int:
            if L <= l and r <= R:
                return self.seg[i]
            mid = (l + r) >> 1
            res = 0
            if L <= mid:
                res = max(res, _query(i << 1, l, mid))
            if R > mid:
                res = max(res, _query(i << 1 | 1, mid + 1, r))
            return res

        return _query(1, 0, self.n - 1)

class MaximizeActiveSectionWithTradeII:
    def maxActiveSectionsAfterTrade(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        ones = s.count('1')
        zero_blocks = []
        block_left = []
        block_right = []

        i = 0
        while i < n:
            start = i
            while i < n and s[i] == s[start]:
                i += 1
            if s[start] == '0':
                zero_blocks.append(i - start)
                block_left.append(start)
                block_right.append(i - 1)
        zero_blocks_size = len(zero_blocks)
        if zero_blocks_size < 2:
            return [ones] * len(queries)
        arr = []
        for i in range(zero_blocks_size - 1):
            arr.append(zero_blocks[i] + zero_blocks[i + 1])
        seg = SegmentTree(arr)
        res = []
        for l, r in queries:
            i = bisect_left(block_right, l)
            j = bisect_right(block_left, r) - 1
            # at most 1 continuous block of 0s within the substring
            if i > zero_blocks_size - 1 or j < 0 or i >= j:
                res.append(ones)
                continue
            # actual length of the first consecutive block of 0s in the substring
            first_len = block_right[i] - max(block_left[i], l) + 1
            # actual length of the last consecutive block of 0s in the substring
            last_len = min(block_right[j], r) - block_left[j] + 1
            # exactly 2 consecutive 0 blocks within the substring
            if i + 1 == j:
                best_gain = first_len + last_len
                res.append(ones + best_gain)
                continue
            val1 = first_len + zero_blocks[i + 1]
            val2 = zero_blocks[j - 1] + last_len
            val3 = seg.query(i + 1, j - 2)
            best_gain = max(val1, val2, val3)
            res.append(ones + best_gain)
        return res