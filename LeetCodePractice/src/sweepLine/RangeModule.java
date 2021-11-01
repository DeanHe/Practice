package sweepLine;

import java.util.*;

/*
#715
A Range Module is a module that tracks ranges of numbers. Your task is to design and implement the following interfaces in an efficient manner.

addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right) is currently being tracked.
removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).

Example 1:
addRange(10, 20): null
removeRange(14, 16): null
queryRange(10, 14): true (Every number in [10, 14) is being tracked)
queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the remove operation)
Note:

A half open interval [left, right) denotes all real numbers left <= x < right.
0 < left < right < 10^9 in all calls to addRange, queryRange, removeRange.
The total number of calls to addRange in a single test case is at most 1000.
The total number of calls to queryRange in a single test case is at most 5000.
The total number of calls to removeRange in a single test case is at most 1000.

hint:
1 Maintain a sorted set of disjoint intervals. addRange and removeRange can be performed with time complexity linear to the size of this set;
queryRange can be performed with time complexity logarithmic to the size of this set.
analysis:
TreeMap<Integer, Integer>, key is the starting index and value is the ending index of the interval.
Maintenance is done to make sure no overlap intervals exist in the Map.
*/
public class RangeModule {
	/**
	 * Your RangeModule object will be instantiated and called as such:
	 * RangeModule obj = new RangeModule();
	 * obj.addRange(left,right);
	 * boolean param_2 = obj.queryRange(left,right);
	 * obj.removeRange(left,right);
	 */
	TreeMap<Integer, Integer> map;
	public RangeModule() {
        map = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
    	// find first and last overlapping interval
        Integer preStart = map.floorKey(left);
        Integer posStart = map.floorKey(right);
        if(preStart == null && posStart == null){
        	map.put(left, right);
        } else if(preStart != null && map.get(preStart) >= left){
        	Integer maxEnd = Math.max(map.get(preStart), right);
        	maxEnd = Math.max(maxEnd, map.get(posStart));
        	map.put(preStart, maxEnd);
        } else {
        	map.put(left, Math.max(map.get(posStart), right));
        }
        // clean up intermediate intervals
        Map<Integer, Integer> overlap = map.subMap(left, false, right, true);
        Set<Integer> set = new HashSet<>(overlap.keySet());
        map.keySet().removeAll(set);
    }
    
    public boolean queryRange(int left, int right) {
        Integer preStart = map.floorKey(left);
        if(preStart == null){
        	return false;
        }
        return map.get(preStart) >= right;
    }
    
    public void removeRange(int left, int right) {
        if(left >= right){
        	return;
        }
        Integer preStart = map.floorKey(left);
        Integer posStart = map.floorKey(right);
        if(posStart != null && map.get(posStart) > right){
        	map.put(right, map.get(posStart));
        }
        if(preStart != null && map.get(preStart) > left){
        	map.put(preStart, left);
        }
     // clean up intermediate intervals
        Map<Integer, Integer> overlap = map.subMap(left, true, right, false);
        Set<Integer> set = new HashSet<>(overlap.keySet());
        map.keySet().removeAll(set);
    }
}
