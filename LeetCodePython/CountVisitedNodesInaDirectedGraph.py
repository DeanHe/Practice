"""
There is a directed graph consisting of n nodes numbered from 0 to n - 1 and n directed edges.

You are given a 0-indexed array edges where edges[i] indicates that there is an edge from node i to node edges[i].

Consider the following process on the graph:

You start from a node x and keep visiting other nodes through edges until you reach a node that you have already visited before on this same process.
Return an array answer where answer[i] is the number of different nodes that you will visit if you perform the process starting from node i.

Example 1:
Input: edges = [1,2,0,0]
Output: [3,3,3,4]
Explanation: We perform the process starting from each node in the following way:
- Starting from node 0, we visit the nodes 0 -> 1 -> 2 -> 0. The number of different nodes we visit is 3.
- Starting from node 1, we visit the nodes 1 -> 2 -> 0 -> 1. The number of different nodes we visit is 3.
- Starting from node 2, we visit the nodes 2 -> 0 -> 1 -> 2. The number of different nodes we visit is 3.
- Starting from node 3, we visit the nodes 3 -> 0 -> 1 -> 2 -> 0. The number of different nodes we visit is 4.

Example 2:
Input: edges = [1,2,3,4,0]
Output: [5,5,5,5,5]
Explanation: Starting from any node we can visit every node in the graph in the process.

Constraints:
n == edges.length
2 <= n <= 10^5
0 <= edges[i] <= n - 1
edges[i] != i

hints:
1 Consider if the graph was only one cycle, what will be the answer for each node?
2 The actual graph will always consist of at least one cycle and some other nodes.
3 Calculate the answer for nodes in cycles the same way as in hint 1. How do you calculate the answer for the remaining nodes?
"""
from collections import defaultdict, deque
from functools import cache
from typing import List


class CountVisitedNodesInaDirectedGraph:
    def countVisitedNodes(self, edges: List[int]) -> List[int]:
        sz = len(edges)
        in_deg = defaultdict(int)
        for v in edges:
            in_deg[v] += 1
        
        # 1. FIND CYCLIC NODES
        q = deque(v for v in range(sz) if in_deg[v] == 0)
        while q:
            for _ in range(len(q)):
                s = q.popleft()
                e = edges[s]
                in_deg[e] -= 1
                if in_deg[e] == 0:
                    q.append(e)
        
        # 2. PROCESS CYCLES
        cnt = defaultdict(int)
        visited = set()
        for v in filter(lambda v: in_deg[v] != 0, range(sz)):
            if v not in visited:
                cycle = []
                while v not in visited:
                    cycle.append(v)
                    visited.add(v)
                    v = edges[v]
                for v in cycle:
                    cnt[v] = len(cycle)

        # 3. PROCESS NON-CYCLIC NODES
        @cache
        def dfs(v):
            if v in cnt:
                return cnt[v]
            return 1 + dfs(edges[v])

        return[dfs(v) for v in range(sz)]
