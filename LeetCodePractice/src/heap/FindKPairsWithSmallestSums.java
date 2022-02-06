package heap;

import java.util.*;

/*
#373

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]] 
Explanation: The first 3 pairs are returned from the sequence: 
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence: 
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]

analysis:
 Use min_heap to keep track on next minimum pair sum, and we only need to maintain K possible candidates in the data structure.
*/
public class FindKPairsWithSmallestSums {
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<int[]> res = new ArrayList<>();
		if (nums1.length == 0 || nums2.length == 0 || k == 0) {
			return res;
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
		for (int i = 0; i < nums1.length && i < k; i++) {
			// similar to merge k sorted list, the last element here is the
			// index of nums2
			pq.offer(new int[] { nums1[i], nums2[0], 0 });
		}
		while (k > 0 && !pq.isEmpty()) {
			int[] cur = pq.poll();
			res.add(new int[] { cur[0], cur[1] });
			int pos = cur[2];
			if(pos + 1 < nums2.length){
				pq.offer(new int[] { cur[0], nums2[pos + 1], pos + 1 });
			}
			k--;
		}
		return res;
        
    }
}
