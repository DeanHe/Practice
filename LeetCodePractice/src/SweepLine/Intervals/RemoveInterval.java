package SweepLine.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.

We remove the intersections between any interval in intervals and the interval toBeRemoved.

Return a sorted list of intervals after all such removals.

Example 1:

Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
Output: [[0,1],[6,7]]
Example 2:

Input: intervals = [[0,5]], toBeRemoved = [2,3]
Output: [[0,2],[3,5]]
Constraints:

1 <= intervals.length <= 10^4
-10^9 <= intervals[i][0] < intervals[i][1] <= 10^9

analysis;
Time Complexity: O(N)
Space Complexity: O(N)
 */
public class RemoveInterval {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for(int[] i : intervals){
            if(i[1] <= toBeRemoved[0] || toBeRemoved[1] <= i[0]){ // not overlap
                res.add(Arrays.asList(i[0], i[1]));
            } else {
                if(i[0] < toBeRemoved[0]){
                    res.add(Arrays.asList(i[0], toBeRemoved[0]));
                }
                if(toBeRemoved[1] < i[1]){
                    res.add(Arrays.asList(toBeRemoved[1], i[1]));
                }
            }
        }
        return res;
    }
}
