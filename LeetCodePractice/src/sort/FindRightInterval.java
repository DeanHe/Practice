package sort;

import java.util.Map;
import java.util.TreeMap;

/*
Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:

You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.


Example 1:

Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.


Example 2:

Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.


Example 3:

Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]

Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.


 */
public class FindRightInterval {
    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        int[] res = new int[len];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < len; i++) {
            int[] interval = intervals[i];
            if (!map.containsKey(interval[0])) {
                map.put(interval[0], i);
            }
        }
        for (int i = 0; i < len; i++) {
            int[] interval = intervals[i];
            Map.Entry<Integer, Integer> ceilingEntry = map.ceilingEntry(interval[1]);
            res[i] = ceilingEntry != null ? ceilingEntry.getValue() : -1;
        }
        return res;
    }
}
