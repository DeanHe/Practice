import java.util.*;

/*There is a process sequence that contains the start and end of each process. There is a query sequence asking how many processes are running at a certain point in time. Please return the query result of the query sequence.
 Given logs = [[1, 1234], [2, 1234]], queries = [1, 1235], return [1, 0].*/
public class ProcessSequence {
	class Interval {
		int start, end;

		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}

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
			List<Integer> temp = new ArrayList<>();
			Map<Integer, Integer> hash = new HashMap<Integer, Integer>();
			for (Interval i : logs) {
				temp.add(i.start);
				temp.add(i.end);
				temp.add(i.end + 1);
			}
			for (int i : queries) {
				temp.add(i);
			}
			Collections.sort(temp);
			int index = 1;
			for (int i : temp) {
				if (!hash.containsKey(i)) {
					hash.put(i, index);
					index++;
				}
			}
			int[] count = new int[index + 1];
			for (Interval i : logs) {
				count[hash.get(i.start)]++;
				count[hash.get(i.end + 1)]--;
			}
			for (int i = 1; i <= index; i++) {
				count[i] += count[i - 1];
			}
			for (int i : queries) {
				ans.add(count[hash.get(i)]);
			}
			return ans;
		}
	}
}
