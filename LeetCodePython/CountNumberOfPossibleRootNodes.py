"""
Alice has an undirected tree with n nodes labeled from 0 to n - 1. The tree is represented as a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Alice wants Bob to find the root of the tree. She allows Bob to make several guesses about her tree. In one guess, he does the following:

Chooses two distinct integers u and v such that there exists an edge [u, v] in the tree.
He tells Alice that u is the parent of v in the tree.
Bob's guesses are represented by a 2D integer array guesses where guesses[j] = [uj, vj] indicates Bob guessed uj to be the parent of vj.

Alice being lazy, does not reply to each of Bob's guesses, but just says that at least k of his guesses are true.

Given the 2D integer arrays edges, guesses and the integer k, return the number of possible nodes that can be the root of Alice's tree. If there is no such tree, return 0.

Example 1:
Input: edges = [[0,1],[1,2],[1,3],[4,2]], guesses = [[1,3],[0,1],[1,0],[2,4]], k = 3
Output: 3
Explanation:
Root = 0, correct guesses = [1,3], [0,1], [2,4]
Root = 1, correct guesses = [1,3], [1,0], [2,4]
Root = 2, correct guesses = [1,3], [1,0], [2,4]
Root = 3, correct guesses = [1,0], [2,4]
Root = 4, correct guesses = [1,3], [1,0]
Considering 0, 1, or 2 as root node leads to 3 correct guesses.

Example 2:
Input: edges = [[0,1],[1,2],[2,3],[3,4]], guesses = [[1,0],[3,4],[2,1],[3,2]], k = 1
Output: 5
Explanation:
Root = 0, correct guesses = [3,4]
Root = 1, correct guesses = [1,0], [3,4]
Root = 2, correct guesses = [1,0], [2,1], [3,4]
Root = 3, correct guesses = [1,0], [2,1], [3,2], [3,4]
Root = 4, correct guesses = [1,0], [2,1], [3,2]
Considering any node as root will give at least 1 correct guess.


Constraints:
edges.length == n - 1
2 <= n <= 10^5
1 <= guesses.length <= 10^5
0 <= ai, bi, uj, vj <= n - 1
ai != bi
uj != vj
edges represents a valid tree.
guesses[j] is an edge of the tree.
guesses is unique.
0 <= k <= guesses.length

hints:
1 How can we check if any node can be the root?
2 Can we use this information to check its neighboring nodes?
3 When we traverse from current node to a neighboring node, how will we update our answer?

TC: O(N)
"""
from collections import defaultdict
from functools import cache
from typing import List


class CountNumberOfPossibleRootNodes:
    def rootCount(self, edges: List[List[int]], guesses: List[List[int]], k: int) -> int:
        res = 0
        graph = defaultdict(list)
        for a, b in edges:
            graph[a].append(b)
            graph[b].append(a)
        guesses = set((a, b) for a, b in guesses)

        @cache
        def count_correct_pairs(cur, parent):
            neighbors = graph[cur]
            corrects = 0
            for nb in neighbors:
                if nb != parent:
                    if (cur, nb) in guesses:
                        corrects += 1
                    corrects += count_correct_pairs(nb, cur)
            return corrects

        for root in graph:
            if count_correct_pairs(root, None) >= k:
                res += 1
        return res