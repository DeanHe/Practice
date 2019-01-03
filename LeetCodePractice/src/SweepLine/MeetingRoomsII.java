package SweepLine;
// https://www.programcreek.com/2014/05/leetcode-meeting-rooms-ii-java/
/*Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example
Given intervals = [(0,30),(5,10),(15,20)], return 2.*/
import java.util.*;

public class MeetingRoomsII {
	class Interval {
		int start, end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public int minMeetingRooms(Interval[] intervals) {
	    if(intervals==null||intervals.length==0)
	        return 0;
	 
	    Arrays.sort(intervals, new Comparator<Interval>(){
	        public int compare(Interval i1, Interval i2){
	            return i1.start-i2.start;
	        }
	    });
	    // use a pq to get the swept interval has min end value
	    PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
	    int count=1;
	    queue.offer(intervals[0].end);
	 
	    for(int i=1; i<intervals.length; i++){
	    	Interval cur = intervals[i];
	        if(cur.start < queue.peek()){
	            count++;	 
	        }else{
	            queue.poll();
	        } 
	        queue.offer(cur.end);
	    }	 
	    return count;
	}

}
