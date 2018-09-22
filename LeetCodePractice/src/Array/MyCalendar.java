package Array;

import java.util.*;

public class MyCalendar {
	TreeMap<Integer, Integer> map;

	public MyCalendar() {
		map = new TreeMap<>();
	}

	public boolean book(int start, int end) {
		Integer pre_interval_start = map.floorKey(start);
		Integer next_interval_start = map.ceilingKey(start);
		Integer pre_interval_end = 0;
		if (pre_interval_start != null) {
			pre_interval_end = map.get(pre_interval_start);
		}
		if ((pre_interval_start == null || start >= pre_interval_end)
				&& (next_interval_start == null || end <= next_interval_start)) {
			map.put(start, end);
			return true;
		} else {
			return false;
		}
	}
}
/**
 * Your MyCalendar object will be instantiated and called as such: MyCalendar
 * obj = new MyCalendar(); boolean param_1 = obj.book(start,end);
 */
