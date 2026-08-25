"""
You are given an integer n and an integer array prices of length n, where prices[i] is the price of apples at shop i.

You are also given a 2D integer array roads, where roads[i] = [ui, vi, costi, taxi] represents a bidirectional road:

ui and vi are the shops connected by the road.
costi is the cost to travel the road without carrying apples.
taxi is the multiplier applied to costi when traveling with apples.
For each shop i, you can either:

Buy apples locally at shop i for prices[i].
Travel empty to any shop j using any number of roads, buy apples for prices[j], and return to shop i while carrying apples, paying cost * tax on each road used for the return trip.
The forward path, where you travel empty, and the return path may be different.

Return an integer array ans of length n, where ans[i] is the minimum total cost to buy apples starting from shop i.



Example 1:

Input: n = 2, prices = [8,3], roads = [[0,1,1,2]]

Output: [6,3]

Explanation:



Shop i	prices[i]	Shop j	prices[j]	costi	taxi	Travel cost	Return cost	Total	Minimum
0	8	1	3	1	2	1	1 * 2 = 2	1 + 2 + 3 = 6	min(8, 6) = 6
1	3	0	8	1	2	1	1 * 2 = 2	1 + 2 + 8 = 11	min(3, 11) = 3
Thus, the answer is [6, 3].

Example 2:

Input: n = 3, prices = [9,4,6], roads = [[0,1,1,3],[1,2,4,2]]

Output: [8,4,6]

Explanation:

​​​​​​​

Shop i	prices[i]	Shop j	prices[j]	costi	taxi	Travel cost	Return cost	Total	Minimum
0	9	1	4	1	3	1	1 * 3 = 3	1 + 3 + 4 = 8	min(9, 8) = 8
1	4	2	6	4	2	4	4 * 2 = 8	4 + 8 + 6 = 18	min(4, 18) = 4
2	6	1	4	4	2	4	4 * 2 = 8	4 + 8 + 4 = 16	min(6, 16) = 6
Thus, the answer is [8, 4, 6].

Example 3:

Input: n = 3, prices = [10,11,1], roads = [[0,2,1,3],[1,2,3,4],[0,1,5,2]]

Output: [5,11,1]

Explanation:

​​​​​​​​​​​​​​

Shop i	prices[i]	Shop j	prices[j]	costi	taxi	Travel cost	Return cost	Total	Minimum
0	10	2	1	1	3	1	1 * 3 = 3	1 + 3 + 1 = 5	min(10, 5) = 5
1	11	2	1	3	4	3	3 * 4 = 12	3 + 12 + 1 = 16	min(11, 16) = 11
2	1	0	10	1	3	1	1 * 3 = 3	1 + 3 + 10 = 14	min(1, 14) = 1
Thus, the answer is [5, 11, 1].



Constraints:
1 <= n <= 1000
prices.length == n
1 <= prices[i] <= 10^9
0 <= roads.length <= min(n × (n - 1) / 2, 2000)
roads[i] = [ui, vi, cost_i, tax_i]
0 <= ui, vi <= n - 1
ui != vi
1 <= cost_i <= 10^9
1 <= tax_i <= 100
There are no repeated edges.

analysis:
Dijkstra algorithm + two graphs
TC:O(V*E*logV)
"""
import heapq
from collections import defaultdict
from typing import List


class MinimumCostToBuyApplesII:
    def minCost(self, n: int, prices: List[int], roads: List[List[int]]) -> List[int]:
        res = [0] * n
        INF = 10 ** 18
        empty_graph = defaultdict(dict)
        carry_graph = defaultdict(dict)
        for a, b, cost, tax in roads:
            empty_graph[a][b] = cost
            empty_graph[b][a] = cost
            carry_graph[a][b] = cost * tax
            carry_graph[b][a] = cost * tax

        for i in range(n):
            # calculate empty distance
            empty_dist = [INF] * n
            pq = [(0, i)]
            while pq:
                dist, cur = heapq.heappop(pq)
                if empty_dist[cur] == INF:
                    empty_dist[cur] = dist
                    for nb, travel in empty_graph[cur].items():
                        if empty_dist[nb] == INF:
                            heapq.heappush(pq, (dist + travel, nb))
            # calculate carry apple distance
            pq = [(0, i)]
            carry_dist = [INF] * n
            while pq:
                dist, cur = heapq.heappop(pq)
                if carry_dist[cur] == INF:
                    carry_dist[cur] = dist
                    for nb, travel in carry_graph[cur].items():
                        if carry_dist[nb] == INF:
                            heapq.heappush(pq, (dist + travel, nb))
            # compare buy apple from itself shop and other shops
            res[i] = prices[i]
            for j in range(n):
                if i != j:
                    res[i] = min(res[i], empty_dist[j] + prices[j] + carry_dist[j])
        return res

    # floyd algorithm is with TC:O(N^3)
    def minCost2(self, n: int, prices: List[int], roads: List[List[int]]) -> List[int]:
        res = [0] * n
        INF = 10 ** 18
        empty_dist = [[INF] * n for _ in range(n)]
        carry_dist = [[INF] * n for _ in range(n)]
        for a, b, cost, tax in roads:
            empty_dist[a][b] = cost
            empty_dist[b][a] = cost
            carry_dist[a][b] = cost * tax
            carry_dist[b][a] = cost * tax
        for k in range(n):
            for i in range(n):
                for j in range(n):
                    if k != i and k != j:
                        empty_dist[i][j] = min(empty_dist[i][j], empty_dist[i][k] + empty_dist[k][j])
                        carry_dist[i][j] = min(carry_dist[i][j], carry_dist[i][k] + carry_dist[k][j])
        for i in range(n):
            res[i] = prices[i]
            for j in range(n):
                if i != j:
                    res[i] = min(res[i], empty_dist[i][j] + prices[j] + carry_dist[j][i])
        return res