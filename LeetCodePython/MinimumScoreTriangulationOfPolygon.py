"""
You have a convex n-sided polygon where each vertex has an integer value. You are given an integer array values where values[i] is the value of the ith vertex in clockwise order.

Polygon triangulation is a process where you divide a polygon into a set of triangles and the vertices of each triangle must also be vertices of the original polygon. Note that no other shapes other than triangles are allowed in the division. This process will result in n - 2 triangles.

You will triangulate the polygon. For each triangle, the weight of that triangle is the product of the values at its vertices. The total score of the triangulation is the sum of these weights over all n - 2 triangles.

Return the minimum possible score that you can achieve with some triangulation of the polygon.

Example 1:
Input: values = [1,2,3]
Output: 6
Explanation: The polygon is already triangulated, and the score of the only triangle is 6.

Example 2:
Input: values = [3,7,4,5]
Output: 144
Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.
The minimum score is 144.

Example 3:
Input: values = [1,3,1,4,1,5]
Output: 13
Explanation: The minimum score triangulation is 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.

Constraints:
n == values.length
3 <= n <= 50
1 <= values[i] <= 100

hints:
1 Without loss of generality, there is a triangle that uses adjacent vertices A[0] and A[N-1] (where N = A.length). Depending on your choice K of it, this breaks down the triangulation into two subproblems A[1:K] and A[K+1:N-1].

analysis:
The connected two points in polygon shares one common edge,
these two points must be one and only one triangles.

Explanation
similar to burst balloons
mem[i][j] means the minimum score to triangulate A[i] ~ A[j],
while there is edge connect A[i] and A[j].
We enumerate all points A[k] with i < k < j to form a triangle.
The score of this triangulation is mem[i][j], mem[i][k] + mem[k][j] + A[i] * A[j] * A[k]

TC: O(N^3)
SC: O(N^2)
"""
import math
from typing import List


class MinimumScoreTriangulationOfPolygon:
    def minScoreTriangulation(self, values: List[int]) -> int:
        sz = len(values)
        dp = [[0] * sz for _ in range(sz)]
        for d in range(2, sz):
            for i in range(sz - d):
                j = i + d
                dp[i][j] = math.inf
                for k in range(i + 1, j):
                    dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[j] * values[k])
        return dp[0][sz - 1]