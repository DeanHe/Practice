package greedy;

import java.util.Arrays;

/*
There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).
There are n + 1 taps located at points [0, 1, ..., n] in the garden.
Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.
Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.

Example 1:
Input: n = 5, ranges = [3,4,1,1,0,0]
Output: 1
Explanation:
The tap at point 0 can cover the interval [-3,3]
The tap at point 1 can cover the interval [-3,5]
The tap at point 2 can cover the interval [1,3]
The tap at point 3 can cover the interval [2,4]
The tap at point 4 can cover the interval [4,4]
The tap at point 5 can cover the interval [5,5]
Opening Only the second tap will water the whole garden [0,5]

Example 2:
Input: n = 3, ranges = [0,0,0,0]
Output: -1
Explanation: Even if you activate all the four taps you cannot water the whole garden.

Example 3:
Input: n = 7, ranges = [1,2,1,0,2,1,0,1]
Output: 3

Example 4:
Input: n = 8, ranges = [4,0,0,0,0,0,0,0,4]
Output: 2

Example 5:
Input: n = 8, ranges = [4,0,0,0,4,0,0,0,4]
Output: 1

Constraints:

1 <= n <= 10^4
ranges.length == n + 1
0 <= ranges[i] <= 100

hint:
Create intervals of the area covered by each tap, sort intervals by the left end.

We need to cover the interval [0, n].
we can start with the first interval and out of all intervals that intersect with it we choose the one that covers the farthest point to the right.

What if there is a gap between intervals that is not covered ? we should stop and return -1 as there is some interval that cannot be covered.
analysis:
greedy
similar to VideoStitching
*/
public class MinimumNumberOfTapsToOpenToWateraGarden {
    public int minTaps(int n, int[] ranges) {
        int len = ranges.length;
        int[][] intervals = new int[len][2];
        for(int i = 0; i < len; i++){
            intervals[i][0] = i - ranges[i];
            intervals[i][1] = i + ranges[i];
        }
        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);
        int count = 0, end = 0, i = 0, mostRight = 0;
        while(i < len){
            if(end < intervals[i][0]){
                return -1;
            }
            while(i < len && intervals[i][0] <= end){
                mostRight = Math.max(mostRight, intervals[i][1]);
                i++;
            }
            count++;
            end = mostRight;
            if(n <= end){
                return count;
            }
        }
        return -1;
    }
}
