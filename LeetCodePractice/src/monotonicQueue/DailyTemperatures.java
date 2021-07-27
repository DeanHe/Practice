package monotonicQueue;
/*
#739

Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
*/
import java.util.*;

public class DailyTemperatures {
	/**
     * @param temperatures: a list of daily temperatures
     * @return: a list of how many days you would have to wait until a warmer temperature
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // Write your code here
        int len = temperatures.length;
        int[] res = new int[len];
        Deque<Integer> deque = new ArrayDeque<>(); //stack is monotonically decreasing
        for(int i = 0; i < len; i++){
            while(!deque.isEmpty() && temperatures[i] > temperatures[deque.peekLast()]){
                int pre_index = deque.pollLast();
                res[pre_index] = i - pre_index;
            }
            deque.offerLast(i);
        }
        return res;
    }
}
