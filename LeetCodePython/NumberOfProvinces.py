"""
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

Example 1:
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

Example 2:
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3


Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
"""
from typing import List


class NumberOfProvinces:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        visited = set()

        def dfs(node):
            for i, val in enumerate(isConnected[node]):
                if val and i not in visited:
                    visited.add(i)
                    dfs(i)

        res = 0
        for i in range(len(isConnected)):
            if i not in visited:
                dfs(i)
                res += 1
        return res
