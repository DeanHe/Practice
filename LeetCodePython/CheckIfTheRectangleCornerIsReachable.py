"""
You are given two positive integers X and Y, and a 2D array circles, where circles[i] = [xi, yi, ri] denotes a circle with center at (xi, yi) and radius ri.

There is a rectangle in the coordinate plane with its bottom left corner at the origin and top right corner at the coordinate (X, Y). You need to check whether there is a path from the bottom left corner to the top right corner such that the entire path lies inside the rectangle, does not touch or lie inside any circle, and touches the rectangle only at the two corners.

Return true if such a path exists, and false otherwise.

Example 1:
Input: X = 3, Y = 4, circles = [[2,1,1]]
Output: true
Explanation:
The black curve shows a possible path between (0, 0) and (3, 4).

Example 2:
Input: X = 3, Y = 3, circles = [[1,1,2]]
Output: false
Explanation:
No path exists from (0, 0) to (3, 3).

Example 3:
Input: X = 3, Y = 3, circles = [[2,1,1],[1,2,1]]
Output: false
Explanation:
No path exists from (0, 0) to (3, 3).

Constraints:
3 <= X, Y <= 10^9
1 <= circles.length <= 1000
circles[i].length == 3
1 <= xi, yi, ri <= 10^9

hints:
1 Create a graph with n + 4 vertices.
2 Vertices 0 to n - 1 represent the circles, vertex n represents upper edge, vertex n + 1 represents right edge, vertex n + 2 represents lower edge, and vertex n + 3 represents left edge.
3 Add an edge between these vertices if they intersect or touch.
4 Answer will be false when any of two sides left-right, left-bottom, right-top or top-bottom are reachable using the edges.

analysis:
Union Find
TC: O(N^2)
SC: O(N)
Check if top left edge: parent[n] can meet right bottom edge: parent[n + 1]. If so, it cut all paths between (0, 0) and (X, Y).
"""
from typing import List


class CheckIfTheRectangleCornerIsReachable:
    def canReachCorner(self, X: int, Y: int, circles: List[List[int]]) -> bool:
        n = len(circles)
        parent = list(range(n + 2))

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
            if root_a < root_b:
                parent[root_b] = root_a
            else:
                parent[root_a] = root_b

        for i in range(n):
            x, y, radius = circles[i]
            if X + radius <= x or Y + radius <= y:
                continue
            if x - radius <= 0 or Y <= y + radius:
                union(n, i)
            if X <= x + radius or y - radius <= 0:
                union(n + 1, i)
            for j in range(i):
                nb_x, nb_y, nb_radius = circles[j]
                if (x - nb_x) ** 2 + (y - nb_y) ** 2 <= (radius + nb_radius) ** 2:
                    union(i, j)
        return find_root(n) != find_root(n + 1)