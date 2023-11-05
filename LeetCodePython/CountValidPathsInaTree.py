"""
There is an undirected tree with n nodes labeled from 1 to n. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the tree.

Return the number of valid paths in the tree.

A path (a, b) is valid if there exists exactly one prime number among the node labels in the path from a to b.

Note that:
The path (a, b) is a sequence of distinct nodes starting with node a and ending with node b such that every two adjacent nodes in the sequence share an edge in the tree.
Path (a, b) and path (b, a) are considered the same and counted only once.

Example 1:
Input: n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
Output: 4
Explanation: The pairs with exactly one prime number on the path between them are:
- (1, 2) since the path from 1 to 2 contains prime number 2.
- (1, 3) since the path from 1 to 3 contains prime number 3.
- (1, 4) since the path from 1 to 4 contains prime number 2.
- (2, 4) since the path from 2 to 4 contains prime number 2.
It can be shown that there are only 4 valid paths.

Example 2:
Input: n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
Output: 6
Explanation: The pairs with exactly one prime number on the path between them are:
- (1, 2) since the path from 1 to 2 contains prime number 2.
- (1, 3) since the path from 1 to 3 contains prime number 3.
- (1, 4) since the path from 1 to 4 contains prime number 2.
- (1, 6) since the path from 1 to 6 contains prime number 3.
- (2, 4) since the path from 2 to 4 contains prime number 2.
- (3, 6) since the path from 3 to 6 contains prime number 3.
It can be shown that there are only 6 valid paths.

Constraints:
1 <= n <= 10^5
edges.length == n - 1
edges[i].length == 2
1 <= ui, vi <= n
The input is generated such that edges represent a valid tree.

hints:
1 Use the sieve of Eratosthenes to find all prime numbers in the range [1, n].****
2 Root the tree at any node.
3 Let dp[i][0] = the number of vertical paths starting from i containing no prime nodes , and dp[i][1] = the number of vertical paths starting from i containing one prime node .
4 If i is not prime, dp[i][0] = sum(dp[child][0]) + 1, and dp[i][1] = sum(dp[child][1]) for each child of i in the rooted tree.
5 If i is prime, dp[i][0] = 0, and dp[i][1] = sum(dp[child][0]) + 1 for each child of i in the rooted tree.
6 For each node i, and using the computed dp matrix, count the number of unordered pairs (a,b) such that lca(a,b) = i, and there exists exactly one prime number on the path from a to b.

analysis: dfs
TC: O(N)
SC: O(N)
"""
from typing import List


class CountValidPathsInaTree:
    def countPaths(self, n: int, edges: List[List[int]]) -> int:
        self.res = 0

        # seive
        is_prime = [True] * (n + 1)
        is_prime[0] = is_prime[1] = False
        for i in range(2, int((n + 1) ** 0.5) + 1):
            if is_prime[i]:
                for j in range(i * i, n + 1, i):
                    is_prime[j] = False

        graph = [[] for _ in range(n + 1)]
        for a, b in edges:
            graph[a].append(b)
            graph[b].append(a)

        def dfs(cur, parent):
            zero_primes = 1 if not is_prime[cur] else 0
            one_primes = 1 if is_prime[cur] else 0
            for nb in graph[cur]:
                if nb != parent:
                    nb_zero_primes, nb_one_primes = dfs(nb, cur)
                    self.res += nb_zero_primes * one_primes + nb_one_primes * zero_primes
                    if is_prime[cur]:
                        one_primes += nb_zero_primes
                    else:
                        zero_primes += nb_zero_primes
                        one_primes += nb_one_primes
            return zero_primes, one_primes

        dfs(1, 0)
        return self.res
