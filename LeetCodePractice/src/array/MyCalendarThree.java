package array;

import java.util.*;
// this is sweep line
public class MyCalendarThree {
	TreeMap<Integer, Integer> map;

	public MyCalendarThree() {
		map = new TreeMap<>();
	}

	public int book(int start, int end) {
		int max_overlap = 0;
		map.put(start, map.getOrDefault(start, 0) + 1);
		map.put(end, map.getOrDefault(end, 0) - 1);
		int temp = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			temp += entry.getValue();
			if (temp > max_overlap) {
				max_overlap = temp;
			}
		}
		return max_overlap;
	}
}
/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */