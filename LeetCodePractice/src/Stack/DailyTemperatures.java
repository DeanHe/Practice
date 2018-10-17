package Stack;

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
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int pre_index = stack.pop();
                res[pre_index] = i - pre_index;
            }
            stack.push(i);
        }
        return res;
    }
}
