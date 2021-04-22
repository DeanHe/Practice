package greedy;

import java.util.Arrays;

/*An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.

Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has size at least 2.

Example 1:
Input: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
Output: 3
Explanation:
Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
Also, there isn't a smaller size set that fulfills the above condition.
Thus, we output the size of this set, which is 3.
Example 2:
Input: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
Output: 5
Explanation:
An example of a minimum sized set is {1, 2, 3, 4, 5}.
Note:

intervals will have length in range [1, 3000].
intervals[i] will have length 2, representing some integer interval.
intervals[i][j] will be an integer in [0, 10^8].*/
public class SetIntersectionSizeAtLeastTwo {
	public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (int[] a, int[] b) -> {
        	if(a[1] != b[1]){
        		return a[1] - b[1];
        	}
        	return b[0] - a[0];
        });
        // known two rightmost point in the set/res
        int left = intervals[0][1] - 1;
        int right = intervals[0][1];
        int res = 2;
        for(int i = 1; i < intervals.length; i++){
        	int[] cur = intervals[i];
        	// one number from the result set is in interval, here only add cur[1] to set
        	if(left < cur[0] && right >= cur[0]){
            	res++;
            	left = right;
            	right = cur[1];
            } else if(right < cur[0]){
            	// no number from the result set is in interval, here add cur[0] and cur[1] to set
            	res += 2;
            	right = cur[1];
            	left = cur[1] - 1;
            }
        }
        return res;
    }
}
