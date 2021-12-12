package greedy;

import java.util.HashMap;
import java.util.Map;

/*
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example:
Input: [D,D,D,Z,Z,D], 2 Output: 12
Task needs to be executed in order with a wait time in between the last one.
D _ _ D _ _ D Z _ _ Z D

Input: [D,D,D,Z,D,D], 2
Output: 13
D _ _ D _ _ DZ _ D _ _ D
*/
public class TaskSchedulerFB {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        for(char c : tasks){
            if(map.containsKey(c)){
                int pre = map.get(c);
                if(i - pre <= n){
                    i = pre + n + 1;
                }
            }
            map.put(c, i);
            i++;
        }
        return i;

    }
}
