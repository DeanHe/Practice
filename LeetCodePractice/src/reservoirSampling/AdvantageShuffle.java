package reservoirSampling;

/*
Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

Return any permutation of A that maximizes its advantage with respect to B.

Example 1:
Input: A = [2,7,11,15], B = [1,10,4,11]
Output: [2,11,7,15]

Example 2:
Input: A = [12,24,8,32], B = [13,25,32,11]
Output: [24,32,8,12]

Note:
1 <= A.length = B.length <= 10000
0 <= A[i] <= 10^9
0 <= B[i] <= 10^9

analysis:
tag: greedy
should always first satisfy the biggest element of B, because they are the hardest to satisfy
if the biggest element of A can not satisfy current value of B, nothing can satisfy
use a pq to sort B from big to small.
use start and end pointer on A to try to greedy fit the requirement
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class AdvantageShuffle {
    public int[] advantageCount(int[] A, int[] B) {
        int len = A.length;
        int[] res = new int[len];
        Arrays.sort(A);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < len; i++) {
            pq.offer(new int[]{B[i], i});
        }
        int s = 0, e = len - 1;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] < A[e]) {
                res[cur[1]] = A[e];
                e--;
            } else {
                res[cur[1]] = A[s];
                s++;
            }
        }
        return res;
    }
}
