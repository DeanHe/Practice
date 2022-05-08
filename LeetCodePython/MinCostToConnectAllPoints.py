"""
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

Example 1:
Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation:

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.

Example 2:
Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18


Constraints:
1 <= points.length <= 1000
-10^6 <= xi, yi <= 10^6
All pairs (xi, yi) are distinct.

hint:
1 Connect each pair of points with a weighted edge, the weight being the manhattan distance between those points.
2 The problem is now the cost of minimum spanning tree in graph with above edges.

analysis:
Prim's algorithm
"""
import heapq
from typing import List


class MinCostToConnectAllPoints:
    def minCostConnectPoints(self, points: List[List[int]]) -> int:

        def manhattan(a, b):
            return abs(a[0] - b[0]) + abs(a[1] - b[1])

        res, n = 0, len(points)
        visited = set()
        # dist, i, j
        pq = [(0, 0, 0)]
        while len(visited) < n:
            dist, i, j = heapq.heappop(pq)
            if j not in visited:
                res += dist
                visited.add(j)
                for k in range(n):
                    if k not in visited and k != j:
                        heapq.heappush(pq, (manhattan(points[j], points[k]), j, k))
        return res
