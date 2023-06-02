"""
There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Each node has an associated price. You are given an integer array price, where price[i] is the price of the ith node.

The price sum of a given path is the sum of the prices of all nodes lying on that path.

Additionally, you are given a 2D integer array trips, where trips[i] = [starti, endi] indicates that you start the ith trip from the node starti and travel to the node endi by any path you like.

Before performing your first trip, you can choose some non-adjacent nodes and halve the prices.

Return the minimum total price sum to perform all the given trips.

Example 1:
Input: n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
Output: 23
Explanation: The diagram above denotes the tree after rooting it at node 2. The first part shows the initial tree and the second part shows the tree after choosing nodes 0, 2, and 3, and making their price half.
For the 1st trip, we choose path [0,1,3]. The price sum of that path is 1 + 2 + 3 = 6.
For the 2nd trip, we choose path [2,1]. The price sum of that path is 2 + 5 = 7.
For the 3rd trip, we choose path [2,1,3]. The price sum of that path is 5 + 2 + 3 = 10.
The total price sum of all trips is 6 + 7 + 10 = 23.
It can be proven, that 23 is the minimum answer that we can achieve.

Example 2:
Input: n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
Output: 1
Explanation: The diagram above denotes the tree after rooting it at node 0. The first part shows the initial tree and the second part shows the tree after choosing node 0, and making its price half.
For the 1st trip, we choose path [0]. The price sum of that path is 1.
The total price sum of all trips is 1. It can be proven, that 1 is the minimum answer that we can achieve.

Constraints:
1 <= n <= 50
edges.length == n - 1
0 <= ai, bi <= n - 1
edges represents a valid tree.
price.length == n
price[i] is an even integer.
1 <= price[i] <= 1000
1 <= trips.length <= 100
0 <= starti, endi <= n - 1

hints:
1 The final answer is the price[i] * freq[i], where freq[i] is the number of times node i was visited during the trip, and price[i] is the final price.
2 To find freq[i] we will use dfs or bfs for each trip and update every node on the path start and end.
3 Finally, to find the final price[i] we will use dynamic programming on the tree. Let dp(v, 0/1) denote the minimum total price with the node v’s price being halved or not.
"""
from collections import defaultdict, Counter
from functools import cache
from typing import List


class MinimizeTheTotalPriceOfTheTrips:
    def minimumTotalPrice(self, n: int, edges: List[List[int]], price: List[int], trips: List[List[int]]) -> int:
        cnt = Counter()
        total_cost = 0
        graph = defaultdict(list)
        for a, b in edges:
            graph[a].append(b)
            graph[b].append(a)

        def dfs(cur, parent, end):
            nonlocal total_cost
            if cur == end:
                return True
            for nb in graph[cur]:
                if nb != parent:
                    if dfs(nb, cur, end):
                        total_cost += price[nb]
                        cnt[nb] += 1
                        return True
            return False

        for start, end in trips:
            total_cost += price[start]
            cnt[start] += 1
            dfs(start, None, end)

        @cache
        def dp(cur, parent, can_reduce):
            res = 0
            if can_reduce:
                res = (price[cur] // 2) * cnt[cur]
            for nb in graph[cur]:
                if nb != parent:
                    if can_reduce:
                        res += dp(nb, cur, False)
                    else:
                        res += max(dp(nb, cur, False), dp(nb, cur, True))
            return res

        save = 0
        for i in range(n):
            save = max(save, dp(i, None, False), dp(i, None, True))
        return total_cost - save

