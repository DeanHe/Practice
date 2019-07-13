package Heap;

import java.util.*;
/*Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.*/
public class TopKFrequentElements {
	public List<Integer> topKFrequent(int[] nums, int k) {
		// count the frequency for each element
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}
		// create a min heap
		PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
			public int compare(Pair a, Pair b) {
				return a.count - b.count;
			}
		});
		// maintain a heap of size k.
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Pair temp = new Pair(entry.getKey(), entry.getValue());
			pq.offer(temp);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		// get all elements from the heap
		List<Integer> res = new ArrayList<>();
		while (!pq.isEmpty()) {
			Pair temp = pq.poll();
			res.add(temp.num);
		}
		// reverse the order
		Collections.reverse(res);
		return res;
	}
}

class Pair {
	int num;
	int count;

	Pair(int num, int count) {
		this.num = num;
		this.count = count;
	}
}
