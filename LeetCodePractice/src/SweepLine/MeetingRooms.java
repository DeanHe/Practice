package SweepLine;
// https://www.programcreek.com/2014/07/leetcode-meeting-rooms-java/
/*Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.

Example
Given intervals = [[0,30],[5,10],[15,20]], return false.*/
import java.util.*;

public class MeetingRooms {
	class Interval {
		int start, end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public boolean canAttendMeetings(Interval[] intervals) {
		if(intervals == null || intervals.length == 0){
			return true;
		}
	    Arrays.sort(intervals, new Comparator<Interval>(){
	        public int compare(Interval a, Interval b){
	            return a.start-b.start;
	        }
	    });
	    Interval pre = intervals[0];
	    for(int i=1; i < intervals.length; i++){
	    	Interval cur = intervals[i];
	        if(pre.end > cur.start){
	            return false;
	        } else {
	        	pre = cur;
	        }
	    }
	 
	    return true;
	}
}
