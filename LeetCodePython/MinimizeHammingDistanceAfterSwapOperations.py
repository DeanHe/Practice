"""
You are given two integer arrays, source and target, both of length n. You are also given an array allowedSwaps where each allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements at index ai and index bi (0-indexed) of array source. Note that you can swap elements at a specific pair of indices multiple times and in any order.

The Hamming distance of two arrays of the same length, source and target, is the number of positions where the elements are different. Formally, it is the number of indices i for 0 <= i <= n-1 where source[i] != target[i] (0-indexed).

Return the minimum Hamming distance of source and target after performing any amount of swap operations on array source.

Example 1:
Input: source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
Output: 1
Explanation: source can be transformed the following way:
- Swap indices 0 and 1: source = [2,1,3,4]
- Swap indices 2 and 3: source = [2,1,4,3]
The Hamming distance of source and target is 1 as they differ in 1 position: index 3.

Example 2:
Input: source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
Output: 2
Explanation: There are no allowed swaps.
The Hamming distance of source and target is 2 as they differ in 2 positions: index 1 and index 2.

Example 3:
Input: source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
Output: 0


Constraints:
n == source.length == target.length
1 <= n <= 10^5
1 <= source[i], target[i] <= 10^5
0 <= allowedSwaps.length <= 10^5
allowedSwaps[i].length == 2
0 <= ai, bi <= n - 1
ai != bi

hints:
1 The source array can be imagined as a graph where each index is a node and each allowedSwaps[i] is an edge.
2 Nodes within the same component can be freely swapped with each other.
3 For each component, find the number of common elements. The elements that are not in common will contribute to the total Hamming distance.

analysis:
union find
TC:O(len(allowedSwaps) + len(source) + len(target)) => O(N)
"""
from collections import defaultdict
from typing import List


class MinimizeHammingDistanceAfterSwapOperations:
    def minimumHammingDistance(self, source: List[int], target: List[int], allowedSwaps: List[List[int]]) -> int:
        res = 0
        n = len(source)
        parent = list(range(n))

        def find_root(x):
            root = x
            while parent[root] != root:
                root = parent[root]
            while parent[x] != root:
                fa = parent[x]
                parent[x] = root
                x = fa
            return root

        def union(a, b):
            root_a = find_root(a)
            root_b = find_root(b)
            if root_a != root_b:
                parent[root_a] = root_b

        root_member_cnt = defaultdict(dict)
        for a, b in allowedSwaps:
            union(a, b)
        for i, num in enumerate(source):
            root = find_root(i)
            if num in root_member_cnt[root]:
                root_member_cnt[root][num] += 1
            else:
                root_member_cnt[root][num] = 1
        for i, num in enumerate(target):
            root = find_root(i)
            if num in root_member_cnt[root]:
                root_member_cnt[root][num] -= 1
                if root_member_cnt[root][num] == 0:
                    del root_member_cnt[root][num]
            else:
                res += 1
        return res

