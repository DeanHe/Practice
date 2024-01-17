"""
You are given a 0-indexed array heights of positive integers, where heights[i] represents the height of the ith building.

If a person is in building i, they can move to any other building j if and only if i < j and heights[i] < heights[j].

You are also given another array queries where queries[i] = [ai, bi]. On the ith query, Alice is in building ai while Bob is in building bi.

Return an array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on the ith query. If Alice and Bob cannot move to a common building on query i, set ans[i] to -1.

Example 1:
Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
Output: [2,5,-1,5,2]
Explanation: In the first query, Alice and Bob can move to building 2 since heights[0] < heights[2] and heights[1] < heights[2].
In the second query, Alice and Bob can move to building 5 since heights[0] < heights[5] and heights[3] < heights[5].
In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
In the fourth query, Alice and Bob can move to building 5 since heights[3] < heights[5] and heights[4] < heights[5].
In the fifth query, Alice and Bob are already in the same building.
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

Example 2:
Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
Output: [7,6,-1,4,6]
Explanation: In the first query, Alice can directly move to Bob's building since heights[0] < heights[7].
In the second query, Alice and Bob can move to building 6 since heights[3] < heights[6] and heights[5] < heights[6].
In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
In the fourth query, Alice and Bob can move to building 4 since heights[3] < heights[4] and heights[0] < heights[4].
In the fifth query, Alice can directly move to Bob's building since heights[1] < heights[6].
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

Constraints:
1 <= heights.length <= 5 * 10^4
1 <= heights[i] <= 109
1 <= queries.length <= 5 * 10^4
queries[i] = [ai, bi]
0 <= ai, bi <= heights.length - 1

hints:
1 For each query [x, y], if x > y, swap x and y. Now, we can assume that x <= y.
2 For each query [x, y], if x == y or heights[x] < heights[y], then the answer is y since x ≤ y.
3 Otherwise, we need to find the smallest index t such that y < t and heights[x] < heights[y]. Note that heights[y] <= heights[x], so heights[x] < heights[t] is a sufficient condition.
4 To find index t for each query, sort the queries in descending order of y. Iterate over the queries while maintaining a monotonic stack which we can binary search over to find index t.

Analysis:
monotonic stack
binary search

TC: O(NlogN)
"""
import bisect
from collections import deque
from typing import List


class FindBuildingWhereAliceAndBobCanMeet:
    def leftmostBuildingQueries(self, heights: List[int], queries: List[List[int]]) -> List[int]:
        res = [0] * len(queries)
        idx = []
        for i, q in enumerate(queries):
            a, b = sorted(q)
            if a == b or heights[a] < heights[b]:
                res[i] = b
            else:
                idx.append((a, b, i))
        r = len(heights) - 1
        stack = deque()
        for a, b, i in sorted(idx, key=lambda x: -x[1]):
            while r > b:
                while stack and heights[r] > heights[stack[0]]:
                    stack.popleft()
                stack.appendleft(r)
                r -= 1
            # note: in here we already have heights[a] > heights[b]
            j = bisect.bisect_right(stack, heights[a], key=lambda x: heights[x])
            res[i] = stack[j] if j < len(stack) else -1
        return res
