package heap;

import java.util.PriorityQueue;

/*
A sorted list A contains 1, plus some number of primes.  Then, for every p < q in the list, we consider the fraction p/q.

What is the K-th smallest fraction considered?  Return your answer as an array of ints, where answer[0] = p and answer[1] = q.

Examples:
Input: A = [1, 2, 3, 5], K = 3
Output: [2, 5]
Explanation:
The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
The third fraction is 2/5.

Input: A = [1, 7], K = 1
Output: [1, 7]
Note:

A will have length between 2 and 2000.
Each A[i] will be between 1 and 30000.
K will be between 1 and A.length * (A.length - 1) / 2.

analysis:
This solution probably doesn't have the best runtime but it's really simple and easy to understand.
Says if the list is [1, 7, 23, 29, 47], we can easily have this table of relationships

1/47  < 1/29    < 1/23 < 1/7
7/47  < 7/29    < 7/23
23/47 < 23/29
29/47
So now the problem becomes "find the kth smallest element of (n-1) sorted list"
Following is my implementation using PriorityQueue,
running time is O(nlogn) O(max(n,k) * logn), space is O(n):
*/

public class KthSmallestPrimeFraction {
	public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int len = A.length;
        //store {idx of numerator, idx of denominator}
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> {
        	//compare n1/d1 with n2/d2
        	int n1 = A[a[0]];
        	int d1 = A[a[1]];
        	int n2 = A[b[0]];
        	int d2 = A[b[1]];
        	return n1 * d2 - n2 * d1;
        });
        for(int i = 0; i < len - 1; i++){
        	pq.offer(new int[]{i, len - 1});
        }
        for(int i = 0; i < K - 1; i++){
        	int[] idx = pq.poll();
        	int ni_idx = idx[0];
        	int di_idx = idx[1];
        	if(di_idx - 1 > ni_idx){
        		idx[1]--;
        		pq.offer(idx);
        	}
        }
        int[] resIdx = pq.poll();
        return new int[]{A[resIdx[0]], A[resIdx[1]]};
    }
}
