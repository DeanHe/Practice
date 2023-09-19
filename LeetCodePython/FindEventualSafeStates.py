"""
There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

Example 1:
Illustration of graph
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.

Example 2:
Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.


Constraints:
n == graph.length
1 <= n <= 10^4
0 <= graph[i].length <= n
0 <= graph[i][j] <= n - 1
graph[i] is sorted in a strictly increasing order.
The graph may contain self-loops.
The number of edges in the graph will be in the range [1, 4 * 10^4].

analysis:
dfs
node can be in 3 state:
0: unvisited
1: not leads to cycle (safe)
2: leads to cycle (unsafe)

TC: O(edges + nodes)
"""
from collections import deque
from typing import List


class FindEventualSafeStates:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        res = []
        n = len(graph)
        state = [0] * n

        def dfs(cur):
            if state[cur] != 0:
                return state[cur] == 1

            state[cur] = 2
            for nb in graph[cur]:
                if not dfs(nb):
                    return False

            state[cur] = 1
            return True

        for i in range(n):
            if dfs(i):
                res.append(i)
        return res

    def eventualSafeNodesBFS(self, graph: List[List[int]]) -> List[int]:
        res = []
        n = len(graph)
        in_deg = [0] * n
        rev_graph = [[] for _ in range(n)]
        for i in range(n):
            for nb in graph[i]:
                rev_graph[nb].append(i)
                in_deg[i] += 1
        q = deque()
        for i in range(n):
            if in_deg[i] == 0:
                q.append(i)
        safe = [False] * n
        while q:
            cur = q.popleft()
            safe[cur] = True
            for nb in rev_graph[cur]:
                in_deg[nb] -= 1
                if in_deg[nb] == 0:
                    q.append(nb)
        for i in range(n):
            if safe[i]:
                res.append(i)
        return res

