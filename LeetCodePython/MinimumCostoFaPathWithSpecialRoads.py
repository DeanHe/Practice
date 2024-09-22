"""
You are given an array start where start = [startX, startY] represents your initial position (startX, startY) in a 2D space. You are also given the array target where target = [targetX, targetY] represents your target position (targetX, targetY).

The cost of going from a position (x1, y1) to any other position in the space (x2, y2) is |x2 - x1| + |y2 - y1|.

There are also some special roads. You are given a 2D array specialRoads where specialRoads[i] = [x1i, y1i, x2i, y2i, costi] indicates that the ith special road can take you from (x1i, y1i) to (x2i, y2i) with a cost equal to costi. You can use each special road any number of times.

Return the minimum cost required to go from (startX, startY) to (targetX, targetY).

Example 1:
Input: start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
Output: 5
Explanation: The optimal path from (1,1) to (4,5) is the following:
- (1,1) -> (1,2). This move has a cost of |1 - 1| + |2 - 1| = 1.
- (1,2) -> (3,3). This move uses the first special edge, the cost is 2.
- (3,3) -> (3,4). This move has a cost of |3 - 3| + |4 - 3| = 1.
- (3,4) -> (4,5). This move uses the second special edge, the cost is 1.
So the total cost is 1 + 2 + 1 + 1 = 5.
It can be shown that we cannot achieve a smaller total cost than 5.

Example 2:
Input: start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
Output: 7
Explanation: It is optimal to not use any special edges and go directly from the starting to the ending position with a cost |5 - 3| + |7 - 2| = 7.

Constraints:
start.length == target.length == 2
1 <= startX <= targetX <= 10^5
1 <= startY <= targetY <= 10^5
1 <= specialRoads.length <= 200
specialRoads[i].length == 5
startX <= x1i, x2i <= targetX
startY <= y1i, y2i <= targetY
1 <= costi <= 10^5

hints:
1 It can be proven that it is optimal to go only to the positions that are either the start or the end of a special road or the target position.
2 Consider all positions given to you as nodes in a graph, and the edges of the graph are the special roads.
3 Now the problem is equivalent to finding the shortest path in a directed graph.
"""
import heapq
from typing import List


class MinimumCostoFaPathWithSpecialRoads:
    def minimumCost(self, start: List[int], target: List[int], specialRoads: List[List[int]]) -> int:
        sr, sc = start
        dr, dc = target
        pq = []
        res = float('inf')
        heapq.heappush(pq, (0, sr, sc))
        dist = {}
        while pq:
            d, r, c = heapq.heappop(pq)
            if (r, c) not in dist:
                dist[(r, c)] = d
                if r == dr and c == dc:
                    return d
                for road_sr, road_sc, road_dr, road_dc, cost in specialRoads:
                    if (road_dr, road_dc) not in dist:
                        heapq.heappush(pq, (d + abs(r - road_dr) + abs(c - road_dc), road_dr, road_dc))
                        heapq.heappush(pq, (d + abs(r - road_sr) + abs(c - road_sc) + cost, road_dr, road_dc))
        for (r, c), d in dist.items():
            res = min(res, abs(r - dr) + abs(c - dc) + d)
        return res

