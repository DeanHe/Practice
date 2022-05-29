"""
You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

Example 1:
Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2

Example 2:
Input: times = [[1,2,1]], n = 2, k = 1
Output: 1

Example 3:
Input: times = [[1,2,1]], n = 2, k = 2
Output: -1

Constraints:
1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)

hint:
1 We visit each node at some time, and if that time is better than the fastest time we've reached this node, we travel along outgoing edges in sorted order. Alternatively, we could use Dijkstra's algorithm.
"""
import collections
import heapq
from typing import List


class NetworkDelayTime:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        dist, graph, pq, res = {}, collections.defaultdict(list), [], 0
        for t in times:
            s, e, travel = t
            graph[s].append((travel, e))
        heapq.heappush(pq, (0, k))
        while pq:
            d, cur = heapq.heappop(pq)
            if cur not in dist:
                dist[cur] = d
                res = max(res, d)
                if cur in graph:
                    for nb_travel, nb in graph[cur]:
                        if nb not in dist:
                            nb_d = nb_travel + d
                            heapq.heappush(pq, (nb_d, nb))
        if len(dist) != n:
            return -1
        return res
