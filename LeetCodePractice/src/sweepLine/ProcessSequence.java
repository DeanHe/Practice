package sweepLine;

import sweepLine.Intervals.Interval;

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
		List<Integer> res = new ArrayList<>();
		List<Integer> axis = new ArrayList<>();
		Map<Integer, Integer> idxMap = new HashMap<>();
		for (Interval i : logs) {
			axis.add(i.start);
			axis.add(i.end + 1);
		}
		for (int q : queries) {
			axis.add(q);
		}
		Collections.sort(axis);
		int index = 1;
		for (int tick : axis) {
			if (!idxMap.containsKey(tick)) {
				idxMap.put(tick, index);
				index++;
			}
		}
		int[] preSum = new int[index + 1];
		for (Interval i : logs) {
			preSum[idxMap.get(i.start)]++;
			preSum[idxMap.get(i.end + 1)]--;
		}
		for (int i = 1; i <= index; i++) {
			preSum[i] += preSum[i - 1];
		}
		for (int q : queries) {
			res.add(preSum[idxMap.get(q)]);
		}
		return res;
	}

	public List<Integer> numberOfProcesses2(List<Interval> logs, List<Integer> queries) {
		// Write your code here
		List<Integer> res = new ArrayList<>();
		TreeMap<Integer, Integer> axis = new TreeMap<>();
		for (Interval i : logs) {
			axis.put(i.start, axis.getOrDefault(i.start, 0 ) + 1);
			axis.put(i.end + 1, axis.getOrDefault(i.end + 1, 0 ) - 1);
		}
		for (int q : queries) {
			axis.putIfAbsent(q, 0);
		}
		for(int key : axis.keySet()){
			Integer preKey = axis.lowerKey(key);
			if(preKey != null){
				int preVal = axis.get(preKey);
				axis.put(key, axis.get(key) + preVal);
			}
		}
		for (int q : queries) {
			res.add(axis.get(q));
		}
		return res;
	}
}
