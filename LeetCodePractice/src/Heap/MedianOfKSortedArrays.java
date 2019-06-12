package Heap;

import java.util.PriorityQueue;

/*
  There are k sorted arrays nums. Find the median of the given k sorted arrays.
  
  Time: O(mlogk), every time we operate the heap, we need log(k) time since we have k elements in the heap all the time.
	Space: O(m). We have to iterate m times to find the target.
 */
public class MedianOfKSortedArrays {
	 /**
     * @param nums: the given k sorted arrays
     * @return: the median of the given k sorted arrays
     */
	public double findMedian(int[][] nums) {
		if(nums == null || nums.length == 0){
			return 0.0;
		}
		int N = 0;
		int K = nums.length;
		PriorityQueue<Node> pq = new PriorityQueue<>(K, (a, b) -> {
			return a.val - b.val;
		});
		for(int r = 0; r < nums.length; r++){
			if(nums[r].length == 0){
				continue;
			}
			Node cur = new Node(r, 0, nums[r][0]);
			pq.offer(cur);
			N += nums[r].length;
		}
		if(N == 0){
			return 0.0;
		}
		if(N % 2 == 0){
			// note that here need to have a new pq instance because once the first part use it 
			// then the second part will keep use it but it's already modified.
			return (find(nums, N / 2 - 1, new PriorityQueue<Node>(pq)) + find(nums, N / 2, pq)) / 2.0;
		} else {
			return find(nums, N / 2, pq) / 1.0;
		}
	}
	
	private int find(int[][] nums, int idx, PriorityQueue<Node> pq) {
		int res = 0;
		for(int i = 0; i <= idx; i++){
			Node cur = pq.poll();
			if(i == idx){
				res = cur.val;
				break;
			}
			if(cur.c + 1 < nums[cur.r].length){
				Node next = new Node(cur.r, cur.c + 1, nums[cur.r][cur.c + 1]);
				pq.offer(next);
			}
		}
		return res;
	}
	
	class Node {
		int r, c, val;
		public Node(int r, int c, int val){
			this.r = r;
			this.c = c;
			this.val = val;
		}
	}
}  
