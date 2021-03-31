package dfs;

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
	int timeValue = 0;
	int minDiff = Integer.MAX_VALUE;
	String result = "";
	ArrayList<Integer> digits;
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
        digits = new ArrayList<>(set);
        int hour = Integer.parseInt(time.substring(0,2));
    	int minute = Integer.parseInt(time.substring(3,5));
        timeValue = hour * 60 + minute;
        dfs(0, "");
        return result;
    }
    private void dfs(int pos, String temp){
    	if(pos == 4){
    		int hour = Integer.parseInt(temp.substring(0,2));
        	int minute = Integer.parseInt(temp.substring(2,4));
            int newTime = hour * 60 + minute;
    		if(newTime == timeValue){
    			return;
    		}
    		int diff = newTime - timeValue;
    		if(diff < 0){
    			diff = 1440 + newTime - timeValue;
    		}
    		if(diff < minDiff){
    			minDiff = diff;
    			result = temp.substring(0, 2) + ":" + temp.substring(2, 4);
    		}
    		return;
    	}
    	int len = digits.size();
    	for(int i = 0; i < len; i++){
    		int cur = digits.get(i);
    		if(pos == 0 && cur > 2){
    			continue; //first letter should be 1 or 2
    		}
    		if(pos == 1 && Integer.parseInt(temp) * 10 + cur > 23){
    			continue;
    		}
    		if(pos == 2 && cur > 5){
    			continue;
    		}
    		if(pos == 3 && Integer.parseInt(temp.substring(2)) * 10 + cur > 59){
    			continue;
    		}
    		dfs(pos + 1,temp + cur);
    	}
    }   
}
