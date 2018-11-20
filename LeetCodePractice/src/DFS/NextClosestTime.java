package DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Have you met this question in a real interview?  
Example
Given time = "19:34", return "19:39".

Explanation: 
The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
Given time = "23:59", return "22:22".

Explanation: 
The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
*/
public class NextClosestTime {
	int diff = Integer.MAX_VALUE;
	String result = "";
	/**
     * @param time: the given time
     * @return: the next closest time
     */
    public String nextClosestTime(String time) {
        Set<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(time.substring(0,1)));
        set.add(Integer.parseInt(time.substring(1,2)));
        set.add(Integer.parseInt(time.substring(3,4)));
        set.add(Integer.parseInt(time.substring(4,5)));
        if(set.size() == 1){
        	return time;
        }
        ArrayList<Integer> digits = new ArrayList<>(set);
        int time = toTime(Integer.parseInt(time.substring(0,2)), Integer.parseInt(time.substring(3,5)));
        
    }
    private void dfs(ArrayList<Integer> digits, int pos, int time, String temp){
    	
    }
    private int toTime(int hour, int minute){
    	return hour * 60 + minute;
    }
    int ti
    
}
