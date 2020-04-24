package DP;
/*Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.

Suppose you triangulate the polygon into N-2 triangles.  For each triangle, the value of that triangle is the product of the labels of the vertices, and the total score of the triangulation is the sum of these values over all N-2 triangles in the triangulation.

Return the smallest possible total score that you can achieve with some triangulation of the polygon.


Example 1:

Input: [1,2,3]
Output: 6
Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
Example 2:



Input: [3,7,4,5]
Output: 144
Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.  The minimum score is 144.

Intuition
The connected two points in polygon shares one common edge,
these two points must be one and only one triangles.


Explanation
mem[i][j] means the minimum score to triangulate A[i] ~ A[j],
while there is edge connect A[i] and A[j].

We enumerate all points A[k] with i < k < j to form a triangle.

The score of this triangulation is mem[i][j], mem[i][k] + mem[k][j] + A[i] * A[j] * A[k]
*/
public class MinimumScoreTriangulationOfPolygon {
	public int minScoreTriangulation(int[] A) {
        int N = A.length;
        int[][] dp = new int[N][N];
        for(int d = 2; d < N; d++){
        	for(int i = 0; i + d < N; i++){
        		int j = i + d;
        		dp[i][j] = Integer.MAX_VALUE;
        		for(int k = i + 1; k < j; k++){
        			dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
        		}
        	}
        }
        return dp[0][N - 1];
    }
}
