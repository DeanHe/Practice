package heap;

import java.util.PriorityQueue;

/*
 You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.
 You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array sum among all possible arrays.

 Example 1:
 Input: mat = [[1,3,11],[2,4,6]], k = 5
 Output: 7
 Explanation: Choosing one element from each row, the first k smallest sum are:
 [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.

 Example 2:
 Input: mat = [[1,3,11],[2,4,6]], k = 9
 Output: 17

 Example 3:
 Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 Output: 9
 Explanation: Choosing one element from each row, the first k smallest sum are:
 [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.
 Example 4:

 Input: mat = [[1,1,10],[2,2,9]], k = 7
 Output: 12


 Constraints:

 m == mat.length
 n == mat.length[i]
 1 <= m, n <= 40
 1 <= k <= min(200, n ^ m)
 1 <= mat[i][j] <= 5000
 mat[i] is a non decreasing array.

 analysis:
 similar to Find K Pairs with Smallest Sums

 Intuition:
 We need to keep at most 200 smallest sums. 1 <= k <= min(200, n ^ m)
 For the input with one row the answer is the smallest k-th element or top of the max priority queue of size k.

 Algorithm:
 Calculate max priority queue of size k for the first row.
 Add the rest rows one by one to the max priority queue and make sure that max priority queue size is less than or equal to k.

 Time: O(m * n * k * log(k))
*/
public class FindTheKthSmallestSumOfaMatrixWithSortedRows {
    public int kthSmallest(int[][] mat, int k) {
        int rows = mat.length, cols = mat[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int c = 0; c < cols; c++) {
            pq.offer(mat[0][c]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        for (int r = 1; r < rows; r++) {
            PriorityQueue<Integer> nextPQ = new PriorityQueue<>((a, b) -> b - a);
            for (int sum : pq) {
                for (int c = 0; c < cols; c++) {
                    nextPQ.offer(sum + mat[r][c]);
                    if (nextPQ.size() > k) {
                        nextPQ.poll();
                    }
                }
            }
            pq = nextPQ;
        }
        return pq.poll();
    }
}
