package sweepLine.Intervals;
/*
#56

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
and return an array of the non-overlapping intervals that cover all the intervals in the input.



Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


Constraints:

1 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^4

analysis:
maintain a pre interval, add pre to result by comparing pre with cur
*/
import java.util.*;

public class MergeIntervals {
	public int[][] merge(int[][] intervals) {
		if(intervals.length < 2){
			return intervals;
		}
		List<int[]> ls = new ArrayList<>();
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		int[] pre = intervals[0];
		for(int i = 1; i < intervals.length; i++){
			int[] cur = intervals[i];
			if(pre[1] < cur[0]){
				ls.add(pre);
				pre = cur;
			} else {
				pre[1] = Math.max(pre[1], cur[1]);
			}
		}
		ls.add(pre);
		int[][] res = new int[ls.size()][2];
		for(int i = 0; i < ls.size(); i++){
			res[i][0] = ls.get(i)[0];
			res[i][1] = ls.get(i)[1];
		}
		return res;
	}

	public int[][] merge2(int[][] intervals) {
		if(intervals.length < 2){
			return intervals;
		}
		List<int[]> ls = new ArrayList<>();
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(int[] i : intervals){
			map.put(i[0], map.getOrDefault(i[0], 0) + 1);
			map.put(i[1], map.getOrDefault(i[1], 0) - 1);
		}
		int cnt = 0, s = Integer.MAX_VALUE, e = Integer.MIN_VALUE;
		for(int i : map.keySet()){
			cnt += map.get(i);
			s = Math.min(s, i);
			e = Math.max(e, i);
			if(cnt == 0){
				ls.add(new int[]{s, e});
				s = Integer.MAX_VALUE;
				e = Integer.MIN_VALUE;
			}
		}
		int[][] res = new int[ls.size()][2];
		for(int i = 0; i < ls.size(); i++){
			res[i][0] = ls.get(i)[0];
			res[i][1] = ls.get(i)[1];
		}
		return res;
	}
}
