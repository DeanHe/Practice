package SweepLine;
/*
given a list of intervals, find which intervals the target number locates in.
Assuming the given intervals don't change, and the searchNum method will be called many times, how can you optimize your solution.
for example: we have interval [1, 5] [2, 4] [6, 8] [2, 5], target 3 locates in [1, 5] [2, 4] [2, 5]
*/
import SweepLine.Intervals.Interval;

import java.util.*;

public class IntervalSearch {
	TreeMap<Integer, Set<Interval>> map;
	int min = Integer.MAX_VALUE;
	int max = Integer.MIN_VALUE;
	public IntervalSearch(ArrayList<Interval> input){
		map = new TreeMap<>();
		ArrayList<Integer> axis = new ArrayList<>();
		for(Interval interval : input){
			min = Math.min(min, interval.start);
			max = Math.max(max, interval.end);
			axis.add(interval.start);
			axis.add(interval.end);
		}
		Collections.sort(axis);
		for(int x : axis){
			for(Interval interval : input){
				if(interval.start <= x && x <= interval.end){
				    map.putIfAbsent(x, new HashSet<>());
					map.get(x).add(interval);
				}
			}
		}
	}
	public Set<Interval> searchNum(int num){
		if(num < min || num > max){
			return new HashSet<>();
		}
		int key = map.floorKey(num);
		return map.get(key);
	}
}
