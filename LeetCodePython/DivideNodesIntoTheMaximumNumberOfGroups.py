"""
You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled from 1 to n.

You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional edge between nodes ai and bi. Notice that the given graph may be disconnected.

Divide the nodes of the graph into m groups (1-indexed) such that:

Each node in the graph belongs to exactly one group.
For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to the group with index x, and bi belongs to the group with index y, then |y - x| = 1.
Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes. Return -1 if it is impossible to group the nodes with the given conditions.

Example 1:
Input: n = 6, edges = [[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]
Output: 4
Explanation: As shown in the image we:
- Add node 5 to the first group.
- Add node 1 to the second group.
- Add nodes 2 and 4 to the third group.
- Add nodes 3 and 6 to the fourth group.
We can see that every edge is satisfied.
It can be shown that that if we create a fifth group and move any node from the third or fourth group to it, at least on of the edges will not be satisfied.

Example 2:
Input: n = 3, edges = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: If we add node 1 to the first group, node 2 to the second group, and node 3 to the third group to satisfy the first two edges, we can see that the third edge will not be satisfied.
It can be shown that no grouping is possible.


Constraints:
1 <= n <= 500
1 <= edges.length <= 10^4
edges[i].length == 2
1 <= ai, bi <= n
ai != bi
There is at most one edge between any pair of vertices.

hints:
1 If the graph is not bipartite, it is not possible to group the nodes.
2 Notice that we can solve the problem for each connected component independently, and the final answer will be just the sum of the maximum number of groups in each component.
3 Finally, to solve the problem for each connected component, we can notice that if for some node v we fix its position to be in the leftmost group, then we can also evaluate the position of every other node. That position is the depth of the node in a bfs tree after rooting at node v.

analysis:
Equivalently, a bipartite graph is a graph that does not contain any odd-length cycles.
try to build the groups starting from 1 to n, and pick the max result
TC: O(vertex * (vertex + edges))
"""
import collections
from typing import List


class DivideNodesIntoTheMaximumNumberOfGroups:
    def magnificentSets(self, n: int, edges: List[List[int]]) -> int:
        graph = collections.defaultdict(list)
        parent = {}
        for i in range(1, n + 1):
            parent[i] = i

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
            root_a, root_b = find_root(a), find_root(b)
            if root_a != root_b:
                parent[root_a] = root_b

        def bfs(x):
            q = collections.deque([(x, 1)])
            visited = {x: 1}
            while q:
                cur, level = q.popleft()
                for nb in graph[cur]:
                    if nb not in visited:
                        visited[nb] = level + 1
                        q.append((nb, level + 1))
                    elif visited[nb] == level:
                        return -1
            return level

        for a, b in edges:
            graph[a].append(b)
            graph[b].append(a)
            union(a, b)
  
        # Store the largest groups in each connected component
        max_groups = collections.defaultdict(int)

        # account for disconnected graph, and check no odd length cycle
        for i in range(1, n + 1):
            levels = bfs(i)
            if levels == -1:
                return -1
            root = find_root(i)
            max_groups[root] = max(max_groups[root], levels)
        return sum(max_groups.values())


