"""
You are given an integer c representing c power stations, each with a unique identifier id from 1 to c (1‑based indexing).

These stations are interconnected via n bidirectional cables, represented by a 2D array connections, where each element connections[i] = [ui, vi] indicates a connection between station ui and station vi. Stations that are directly or indirectly connected form a power grid.

Initially, all stations are online (operational).

You are also given a 2D array queries, where each query is one of the following two types:

[1, x]: A maintenance check is requested for station x. If station x is online, it resolves the check by itself. If station x is offline, the check is resolved by the operational station with the smallest id in the same power grid as x. If no operational station exists in that grid, return -1.

[2, x]: Station x goes offline (i.e., it becomes non-operational).

Return an array of integers representing the results of each query of type [1, x] in the order they appear.

Note: The power grid preserves its structure; an offline (non‑operational) node remains part of its grid and taking it offline does not alter connectivity.

Example 1:
Input: c = 5, connections = [[1,2],[2,3],[3,4],[4,5]], queries = [[1,3],[2,1],[1,1],[2,2],[1,2]]
Output: [3,2,3]
Explanation:

Initially, all stations {1, 2, 3, 4, 5} are online and form a single power grid.
Query [1,3]: Station 3 is online, so the maintenance check is resolved by station 3.
Query [2,1]: Station 1 goes offline. The remaining online stations are {2, 3, 4, 5}.
Query [1,1]: Station 1 is offline, so the check is resolved by the operational station with the smallest id among {2, 3, 4, 5}, which is station 2.
Query [2,2]: Station 2 goes offline. The remaining online stations are {3, 4, 5}.
Query [1,2]: Station 2 is offline, so the check is resolved by the operational station with the smallest id among {3, 4, 5}, which is station 3.

Example 2:
Input: c = 3, connections = [], queries = [[1,1],[2,1],[1,1]]
Output: [1,-1]
Explanation:

There are no connections, so each station is its own isolated grid.
Query [1,1]: Station 1 is online in its isolated grid, so the maintenance check is resolved by station 1.
Query [2,1]: Station 1 goes offline.
Query [1,1]: Station 1 is offline and there are no other stations in its grid, so the result is -1.


Constraints:
1 <= c <= 10^5
0 <= n == connections.length <= min(10^5, c * (c - 1) / 2)
connections[i].length == 2
1 <= ui, vi <= c
ui != vi
1 <= queries.length <= 2 * 10^5
queries[i].length == 2
queries[i][0] is either 1 or 2.
1 <= queries[i][1] <= c

hints:
1 Use DFS or BFS to assign each station a component ID
2 For each component, maintain a sorted set of online station IDs
3 For query [2, x], remove x from the set of its component
4 For query [1, x], if x is in its component’s set return x; otherwise if the set is non-empty return its smallest element; else return -1
5 Precompute all components and then handle each query in O(log n) time using the sorted sets

analysis:
Maintaining the minimum identifier of the currently online power stations
Consider processing the queries in reverse order. From this perspective, an offline operation effectively becomes an online operation.
As each power plant comes back online, maintaining the minimum identifier among all online power plants simply requires repeatedly taking the min.

Union Find
TC: O( (connections+queries)×log(C) )
"""
from typing import List


class PowerGridMaintenance:
    def processQueries(self, c: int, connections: List[List[int]], queries: List[List[int]]) -> List[int]:
        res = []
        parent = list(range(c + 1))
        online = [True] * (c + 1)
        offline_cnt = [0] * (c + 1)
        min_online_station = {}

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
            root_a = find_root(a)
            root_b = find_root(b)
            if root_a < root_b:
                parent[root_b] = parent[root_a]
            elif root_b < root_a:
                parent[root_a] = parent[root_b]

        for a, b in connections:
            union(a, b)

        for op, x in queries:
            if op == 2:
                online[x] = False
                offline_cnt[x] += 1

        for x in range(1, c + 1):
            root_x = find_root(x)
            if root_x not in min_online_station:
                min_online_station[root_x] = -1
            ms = min_online_station[root_x]
            if online[x]:
                if ms == -1 or ms > x:
                    min_online_station[root_x] = x

        for i in range(len(queries) - 1, -1, -1):
            op, x = queries[i]
            root_x = find_root(x)
            ms = min_online_station[root_x]
            if op == 1:
                if online[x]:
                    res.append(x)
                else:
                    res.append(ms)
            elif op == 2:
                if offline_cnt[x] > 1:
                    offline_cnt[x] -= 1
                else:
                    online[x] = True
                    if ms == -1 or ms > x:
                        min_online_station[root_x] = x
        return res[::-1]
