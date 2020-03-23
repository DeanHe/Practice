package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/*There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.

Example 1:

Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
 

Note:

1 <= K <= N <= 10000, where N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10^-5 of the correct answer will be considered correct.

Explanation:
So to minimize the total wage, we want a small ratio.
So we sort all workers with their expected ratio, and pick up K first worker.
Now we have a minimum possible ratio for K worker and we count their total quality.

As we pick up next worker with bigger ratio, we increase the ratio for whole group.
Meanwhile we remove a worker with highest quality so that we keep K workers in the group.
We calculate the current ratio * total quality = total wage for this group.

We redo the process and we can find the minimum total wage.
Because workers are sorted by ratio of wage/quality.
For every ratio, we find the minimum possible total quality of K workers.
*/
public class MinimumCostToHireKworkers {
	public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
		int len = quality.length;
		Worker[] workers = new Worker[len];
		for (int i = 0; i < len; i++) {
			workers[i] = new Worker(quality[i], wage[i]);
		}
		Arrays.sort(workers, (a, b) -> Double.compare(a.ratio, b.ratio));
		PriorityQueue<Worker> pq = new PriorityQueue<>((a, b) -> b.quality - a.quality);
		int qualitySum = 0;
		double res = Double.MAX_VALUE;
		for (Worker w : workers) {
			if (pq.size() >= K) {
				Worker most = pq.poll();
				qualitySum -= most.quality;
			}
			pq.offer(w);
			qualitySum += w.quality;
			if (pq.size() == K) {
				res = Math.min(res, qualitySum * w.ratio);
			}
		}
		return res;
	}
}

class Worker {
	int quality, wage;
	double ratio;

	public Worker(int quality, int wage) {
		this.quality = quality;
		this.wage = wage;
		ratio = (wage + 0.0) / quality;
	}
}
