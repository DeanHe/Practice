"""
You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.

You are also given a string s of length n, where s[i] is the character assigned to node i.

Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.

Example 1:
Input: parent = [-1,0,0,1,1,2], s = "abacbe"
Output: 3
Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
It can be proven that there is no longer path that satisfies the conditions.

Example 2:
Input: parent = [-1,0,0,0], s = "aabc"
Output: 3
Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.

Constraints:
n == parent.length == s.length
1 <= n <= 10^5
0 <= parent[i] <= n - 1 for all i >= 1
parent[0] == -1
parent represents a valid tree.
s consists of only lowercase English letters.
"""
from typing import List


class Node:
    def __init__(self, c=None):
        self.c = c
        self.children = []


class LongestPathWithDifferentAdjacentCharacters:
    def longestPath(self, parent: List[int], s: str) -> int:
        n = len(s)
        graph = {}
        for i in range(n):
            graph[i] = Node(s[i])
        for i in range(n):
            if parent[i] != -1:
                graph[parent[i]].children.append(graph[i])
        root = graph[0]

        def dfs(cur):
            max_len, first, second = 1, 0, 0
            for child in cur.children:
                child_single_len, child_max_len = dfs(child)
                if child.c != cur.c:
                    if first < child_single_len:
                        second = first
                        first = child_single_len
                    elif second < child_single_len:
                        second = child_single_len
                max_len = max(max_len, child_max_len)
            max_len = max(max_len, 1 + first + second)
            return 1 + first, max_len

        _, res = dfs(root)
        return res
