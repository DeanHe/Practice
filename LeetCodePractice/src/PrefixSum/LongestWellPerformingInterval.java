package PrefixSum;

import java.util.*;

/*We are given hours, a list of the number of hours worked per day for a given employee.

A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.

A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.

Return the length of the longest well-performing interval.

Example 1:

Input: hours = [9,9,6,0,6,6,9]
Output: 3
Explanation: The longest well-performing interval is [9,9,6].
 
Constraints:

1 <= hours.length <= 10000
0 <= hours[i] <= 16

solution:
1 find longest subarray with sum == 1
2 if sum(array[0:n]) > 0, return n;
*/
public class LongestWellPerformingInterval {
	public int longestWPI(int[] hours) {
        int len = hours.length;
        int res = 0;
        int sum = 0;
        Map<Integer, Integer> visited = new HashMap<>(); // preSum :  preSum's end index
        for(int i = 0; i < len; i++){
            sum += hours[i] > 8 ? 1 : -1;
            if(sum > 0) {
            	res = i + 1;
            } else {
            	if(visited.containsKey(sum - 1)) {
            		res = Math.max(res, i - visited.get(sum - 1));
            	}
            	visited.putIfAbsent(sum, i);
            }
        }
        return res;
    }
}
