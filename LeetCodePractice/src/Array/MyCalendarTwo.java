package Array;

import java.util.*;
//Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
public class MyCalendarTwo {
	List<int[]> calendar;
    List<int[]> overlaps;

    MyCalendarTwo() {
        calendar = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for(int[] io : overlaps){
            // here check intersection
            // for not intersection, the rule is e1 <= s2 OR s1 >= e2
            // by de morgan's law, it is equal to  e1 > s2 AND s1 < e2 ---- so it is for intersection
            if(end > io[0] && start < io[1]){
                return false;
            }
        }
        for(int[] ic : calendar){
            if(end > ic[0] && start < ic[1]){
                overlaps.add(new int[]{Math.max(start, ic[0]), Math.min(end, ic[1])});
            }
        }
        calendar.add(new int[] {start , end});
        return true;
    }
}
/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */