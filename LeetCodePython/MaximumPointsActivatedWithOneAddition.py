"""
You are given a 2D integer array points, where points[i] = [xi, yi] represents the coordinates of the ith point. All coordinates in points are distinct.

If a point is activated, then all points that have the same x-coordinate or y-coordinate become activated as well.

Activation continues until no additional points can be activated.

You may add one additional point at any integer coordinate (x, y) not already present in points. Activation begins by activating this newly added point.

Return an integer denoting the maximum number of points that can be activated, including the newly added point.

Example 1:
Input: points = [[1,1],[1,2],[2,2]]
Output: 4

Explanation:
Adding and activating a point such as (1, 3) causes activations:

(1, 3) shares x = 1 with (1, 1) and (1, 2) -> (1, 1) and (1, 2) become activated.
(1, 2) shares y = 2 with (2, 2) -> (2, 2) becomes activated.
Thus, the activated points are (1, 3), (1, 1), (1, 2), (2, 2), so 4 points in total. We can show this is the maximum activated.

Example 2:
Input: points = [[2,2],[1,1],[3,3]]
Output: 3

Explanation:
Adding and activating a point such as (1, 2) causes activations:

(1, 2) shares x = 1 with (1, 1) -> (1, 1) becomes activated.
(1, 2) shares y = 2 with (2, 2) -> (2, 2) becomes activated.
Thus, the activated points are (1, 2), (1, 1), (2, 2), so 3 points in total. We can show this is the maximum activated.

Example 3:
Input: points = [[2,3],[2,2],[1,1],[4,5]]
Output: 4

Explanation:
Adding and activating a point such as (2, 1) causes activations:

(2, 1) shares x = 2 with (2, 3) and (2, 2) -> (2, 3) and (2, 2) become activated.
(2, 1) shares y = 1 with (1, 1) -> (1, 1) becomes activated.
Thus, the activated points are (2, 1), (2, 3), (2, 2), (1, 1), so 4 points in total.

Constraints:
1 <= points.length <= 10^5
points[i] = [xi, yi]
-109 <= xi, yi <= 10^9
points contains all distinct coordinates.

hints:
1 Use disjoint-set union (DSU).
2 Build components by unioning points that share the same x or the same y.
3 Each x maps to one component and each y maps to one component. Adding (x0, y0) connects at most two distinct components; activated = size(A) + size(B) + 1 (if same component then size(A) + 1).
4 Maximize by choosing the two components with largest size; if only one component, answer = n + 1.

analysis:
TC:O(N)
SC:O(N)
"""


class MaximumPointsActivatedWithOneAddition:
    def maxActivated(self, points: list[list[int]]) -> int:
        uf = UnionFind(len(points))
        x_root = {}
        y_root = {}
        for i, (x, y) in enumerate(points):
            if x not in x_root:
                x_root[x] = i
            else:
                uf.union(i, x_root[x])
            if y not in y_root:
                y_root[y] = i
            else:
                uf.union(i, y_root[y])
        group_sizes = []
        for i in range(len(points)):
            if uf.parent[i] == i:
                group_sizes.append(uf.size[i])
        group_sizes.sort(reverse=True)
        most = group_sizes[0] if len(group_sizes) > 0 else 0
        second_most = group_sizes[1] if len(group_sizes) > 1 else 0
        return most + second_most + 1

class UnionFind:
    def __init__(self, n: int):
        self.parent = list(range(n))
        self.size = [1] * n

    def find_root(self, x: int) -> int:
        root = x
        while root != self.parent[root]:
            root = self.parent[root]
        while self.parent[x] != root:
            fa = self.parent[x]
            self.parent[x] = root
            x = fa
        return root

    def union(self, a: int, b: int) -> bool:
        a_root, b_root = self.find_root(a), self.find_root(b)
        if a_root == b_root:
            return False
        if self.size[b_root] < self.size[a_root]:
            a_root, b_root = b_root, a_root
        self.parent[a_root] = b_root
        self.size[b_root] += self.size[a_root]
        self.size[a_root] = 0
        return True