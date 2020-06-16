package SweepLine;

import java.util.*;

/*
There is a process sequence that contains the start and end of each process.
There is a query sequence asking how many processes are running at a certain point in time. Please return the query result of the query sequence.
 Given logs = [[1, 1234], [2, 1234]], queries = [1, 1235], return [1, 0].
 */
public class ProcessSequence {

	/**
	 * @param logs:
	 *            Sequence of processes
	 * @param queries:
	 *            Sequence of queries
	 * @return: Return the number of processes
	 */
	public List<Integer> numberOfProcesses(List<Interval> logs, List<Integer> queries) {
		// Write your code here
		List<Integer> ans = new ArrayList<>();
		List<Integer> timeline = new ArrayList<>();
		Map<Integer, Integer> tagToIdx = new HashMap<>();
		for (Interval i : logs) {
			timeline.add(i.start);
			timeline.add(i.end);
			timeline.add(i.end + 1);
		}
		for (int q : queries) {
			timeline.add(q);
		}
		Collections.sort(timeline);
		int index = 1;
		for (int tag : timeline) {
			if (!tagToIdx.containsKey(tag)) {
				tagToIdx.put(tag, index);
				index++;
			}
		}
		int[] preSum = new int[index + 1];
		for (Interval i : logs) {
			preSum[tagToIdx.get(i.start)]++;
			preSum[tagToIdx.get(i.end + 1)]--;
		}
		for (int i = 1; i <= index; i++) {
			preSum[i] += preSum[i - 1];
		}
		for (int q : queries) {
			ans.add(preSum[tagToIdx.get(q)]);
		}
		return ans;
	}
}
