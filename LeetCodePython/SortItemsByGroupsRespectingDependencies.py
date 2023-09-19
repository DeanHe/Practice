"""
There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.

Return a sorted list of the items such that:

The items that belong to the same group are next to each other in the sorted list.
There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
Return any solution if there is more than one solution and return an empty list if there is no solution.

Example 1:
Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
Output: [6,3,4,1,5,2,0,7]

Example 2:
Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
Output: []
Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.


Constraints:
1 <= m <= n <= 3 * 104
group.length == beforeItems.length == n
-1 <= group[i] <= m - 1
0 <= beforeItems[i].length <= n - 1
0 <= beforeItems[i][j] <= n - 1
i != beforeItems[i][j]
beforeItems[i] does not contain duplicates elements.

hints:
1 Think of it as a graph problem.
2 We need to find a topological order on the dependency graph.
3 Build two graphs, one for the groups and another for the items.

analysis:
topological sort
TC: O(N^2)
"""
from collections import deque, defaultdict
from typing import List


class SortItemsByGroupsRespectingDependencies:
    def sortItems(self, n: int, m: int, group: List[int], beforeItems: List[List[int]]) -> List[int]:
        group_id = m
        # assign an unique group id to free item
        for i in range(n):
            if group[i] == -1:
                group[i] = group_id
                group_id += 1

        item_graph = [[] for _ in range(n)]
        item_indeg = [0] * n
        group_graph = [[] for _ in range(group_id)]
        group_indeg = [0] * group_id

        for cur in range(n):
            for pre in beforeItems[cur]:
                item_graph[pre].append(cur)
                item_indeg[cur] += 1
                if group[cur] != group[pre]:
                    group_graph[group[pre]].append(group[cur])
                    group_indeg[group[cur]] += 1

        def topologicalSort(graph, indeg):
            res = []
            q= deque([node for node in range(len(graph)) if indeg[node] == 0])
            while q:
                cur = q.popleft()
                res.append(cur)
                for nb in graph[cur]:
                    indeg[nb] -= 1
                    if indeg[nb] == 0:
                        q.append(nb)
            # possible cycle dependency
            return res if len(res) == len(graph) else []

        item_order = topologicalSort(item_graph, item_indeg)
        group_order = topologicalSort(group_graph, group_indeg)

        if not item_order or not group_order:
            return []

        ordered_groups = defaultdict(list)
        for i in item_order:
            ordered_groups[group[i]].append(i)

        res = []
        for g in group_order:
            res += ordered_groups[g]
        return res