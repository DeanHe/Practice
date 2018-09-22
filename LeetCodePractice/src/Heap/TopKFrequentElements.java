package Heap;

import java.util.*;

public class TopKFrequentElements {
	public List<Integer> topKFrequent(int[] nums, int k) {
		// count the frequency for each element
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int n : nums) {
			if (map.containsKey(n)) {
				int temp = map.get(n);
				map.put(n, temp + 1);
			} else {
				map.put(n, 1);
			}
		}
		// create a min heap
		PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
			public int compare(Pair a, Pair b) {
				return a.count - b.count;
			}
		});
		// maintain a heap of size k.
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Pair temp = new Pair(entry.getKey(), entry.getValue());
			queue.offer(temp);
			if (queue.size() > k) {
				queue.poll();
			}
		}
		// get all elements from the heap
		List<Integer> res = new ArrayList<>();
		while (!queue.isEmpty()) {
			Pair temp = queue.poll();
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
