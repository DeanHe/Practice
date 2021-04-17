package sweepLine.Intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/*
We are given a list schedule of employees, which represents the working time for each employee.
Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:
Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

Example 2:
Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:
schedule and schedule[i] are lists with lengths in range [1, 50].
0 <= schedule[i].start < schedule[i].end <= 10^8.
 */
public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        List<Interval> schedules = new ArrayList<>();
        for(List<Interval> sch : schedule){
            for(Interval interval : sch){
                schedules.add(interval);
            }
        }
        Collections.sort(schedules, (a, b) -> a.start - b.start);
        int maxEnd = -1;
        for(Interval cur : schedules){
            if(maxEnd != -1 && maxEnd < cur.start){
                res.add(new Interval(maxEnd, cur.start));
            }
            maxEnd = Math.max(maxEnd, cur.end);
        }
        return res;
    }

    public List<Interval> employeeFreeTimeII(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        List<Interval> schedules = new ArrayList<>();
        for(List<Interval> sch : schedule){
            for(Interval i : sch){
                map.put(i.start, map.getOrDefault(i.start, 0) + 1);
                map.put(i.end, map.getOrDefault(i.end, 0) - 1);
            }
        }
        int sum = 0, s = 0;
        boolean freeTime = false;
        for(int i : map.keySet()){
            sum += map.get(i);
            if(sum == 0){
                s = i;
                freeTime = true;
            } else if(sum > 0 && freeTime){
                res.add(new Interval(s, i));
                freeTime = false;
            }
        }
        return res;
    }
}
