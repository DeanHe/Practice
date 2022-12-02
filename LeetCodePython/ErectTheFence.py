"""
You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.

You are asked to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed.

Return the coordinates of trees that are exactly located on the fence perimeter.



Example 1:
Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]

Example 2:
Input: points = [[1,2],[2,2],[4,2]]
Output: [[4,2],[2,2],[1,2]]


Constraints:
1 <= points.length <= 3000
points[i].length == 2
0 <= xi, yi <= 100
All the given points are unique.

analysis:
https://algorithmist.com/wiki/Monotone_chain_convex_hull
TC O(N log N)
SC O(N)
"""
from typing import List


class ErectTheFence:
    def outerTrees(self, trees: List[List[int]]) -> List[List[int]]:
        def orientation(pre, cur, nxt):
            return (cur[1] - pre[1]) * (nxt[0] - cur[0]) - (nxt[1] - cur[1]) * (cur[0] - pre[0])

        n = len(trees)
        trees.sort()
        hull = []
        for point in trees:
            while len(hull) >= 2 and orientation(hull[-2], hull[-1], point) > 0:
                hull.pop()
            hull.append(point)
        hull.pop()
        for point in trees[::-1]:
            while len(hull) >= 2 and orientation(hull[-2], hull[-1], point) > 0:
                hull.pop()
            hull.append(point)
        return [list(x) for x in set(tuple(point) for point in hull)]
