"""
There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

Example 1:
Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.

Example 2:
Input: n = 2, connections = [[0,1]]
Output: [[0,1]]

Constraints:
2 <= n <= 105
n - 1 <= connections.length <= 105
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.

hint:
1 Use Tarjan's algorithm.

"""
import collections
from typing import List


class CriticalConnectionsInaNetwork:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:
        res, time, graph = [], {}, collections.defaultdict(list)
        for s, e in connections:
            graph[s].append(e)
            graph[e].append(s)

        def dfs(cur, pre, t):
            time[cur] = t
            for nb in graph[cur]:
                if nb != pre:
                    if nb not in time:
                        dfs(nb, cur, t + 1)
                    time[cur] = min(time[cur], time[nb])
                    if t < time[nb]:
                        res.append([cur, nb])

        dfs(0, -1, 0)
        return res
