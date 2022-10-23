"""
There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.

You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.

Example 1:
Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.

Example 2:
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2

Example 3:
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.

Constraints:
1 <= n <= 10^5
1 <= connections.length <= min(n * (n - 1) / 2, 105)
connections[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated connections.
No two computers are connected by more than one cable.

hints:
1 As long as there are at least (n - 1) connections, there is definitely a way to connect all computers.
2 Use DFS to determine the number of isolated computer clusters.
"""
from typing import List


class NumberOfOperationsToMakeNetworkConnected:
    def makeConnected(self, n: int, connections: List[List[int]]) -> int:
        self.parent = list(range(n))
        cnt = n
        extra_edges = 0
        for a, b in connections:
            a_root, b_root = self.find_root(a), self.find_root(b)
            if a_root == b_root:
                extra_edges += 1
            else:
                self.parent[a_root] = b_root
                cnt -= 1
        if extra_edges >= cnt - 1:
            return cnt - 1
        return -1

    def find_root(self, x):
        root = x
        while self.parent[root] != root:
            root = self.parent[root]
        while x != root:
            fa = self.parent[x]
            self.parent[x] = root
            x = fa
        return root