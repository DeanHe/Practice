"""
You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.



Example 1:

Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination:
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"
Example 2:

Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
Output: "abcd"
Explaination:
Swap s[0] and s[3], s = "bcad"
Swap s[0] and s[2], s = "acbd"
Swap s[1] and s[2], s = "abcd"
Example 3:

Input: s = "cba", pairs = [[0,1],[1,2]]
Output: "abc"
Explaination:
Swap s[0] and s[1], s = "bca"
Swap s[1] and s[2], s = "bac"
Swap s[0] and s[1], s = "abc"


Constraints:
1 <= s.length <= 10^5
0 <= pairs.length <= 10^5
0 <= pairs[i][0], pairs[i][1] < s.length
s only contains lower case English letters.

hint:
1 Think of it as a graph problem.
2 Consider the pairs as connected nodes in the graph, what can you do with a connected component of indices ?
3 We can sort each connected component alone to get the lexicographically minimum string.
"""
import collections
from typing import List


class SmallestStringWithSwaps:
    def smallestStringWithSwaps(self, s: str, pairs: List[List[int]]) -> str:
        parent = list(range(len(s)))

        def findRoot(x):
            root = x
            while parent[root] != root:
                root = parent[root]
            while x != root:
                fa = parent[x]
                parent[x] = root
                x = fa
            return fa

        def union(a, b):
            root_a = findRoot(a)
            root_b = findRoot(b)
            if root_a != root_b:
                parent[root_a] = root_b

        res, graph = [], collections.defaultdict(list)
        for a, b in pairs:
            union(a, b)
        for i in range(len(s)):
            graph[findRoot(i)].append(s[i])
        for i in graph.keys():
            graph[i].sort(reverse=True)
        for i in range(len(s)):
            res.append(graph[findRoot(i)].pop())
        return ''.join(res)
