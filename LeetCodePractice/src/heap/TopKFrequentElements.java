package heap;

import java.util.*;
/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

analysis:
1 priority queue TC O(n log k)
2 bucket sort TC O(n)
*/
public class TopKFrequentElements {
	public List<Integer> topKFrequent(int[] nums, int k) {
		// count the frequency for each element
		HashMap<Integer, Integer> freq = new HashMap<>();
		for (int n : nums) {
			freq.put(n, freq.getOrDefault(n, 0) + 1);
		}
		// create a min heap
		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.count - b.count);
		// maintain a heap of size k.
		for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
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

	//bucket sort
	public List<Integer> topKFrequent2(int[] nums, int k) {
		List<Integer>[] buckets = new List[nums.length + 1];
		Map<Integer, Integer> freq = new HashMap<>();
		for(int n : nums){
			freq.put(n, freq.getOrDefault(n, 0) + 1);
		}
		for(int key : freq.keySet()){
			int frequency = freq.get(key);
			if(buckets[frequency] == null){
				buckets[frequency] = new ArrayList<>();
			}
			buckets[frequency].add(key);
		}
		List<Integer> res = new ArrayList<>();
		for(int i = buckets.length - 1; i >= 0 && res.size() < k; i--){
			if(buckets[i] != null){
				res.addAll(buckets[i]);
			}
		}
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
