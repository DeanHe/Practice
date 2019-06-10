package SweepLine;

import java.util.List;
import java.util.ArrayList;

/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

 

Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 

Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
*/
public class IntervalListIntersections {
	public int[][] intervalIntersection(int[][] A, int[][] B) {
        if(A == null || A.length == 0 || B == null || B.length == 0){
        	return new int[0][0];
        }
        int Alen = A.length;
        int Blen = B.length;
        List<Interval> res = new ArrayList<>();
        int i = 0, j = 0;
        while(i < Alen && j < Blen){
        	Interval a = new Interval(A[i][0], A[i][1]);
        	Interval b = new Interval(B[j][0], B[j][1]);
        	int startMax = Math.max(a.start, b.start);
        	int endMin = Math.min(a.end, b.end);
        	if(startMax <= endMin){
        		res.add(new Interval(startMax, endMin));
        	}
        	if(a.end == endMin){
        		i++;
        	}
        	if(b.end == endMin){
        		j++;
        	}
        }
        int[][] ans = new int[res.size()][2];
        for(int k = 0; k < res.size(); k++){
        	Interval a = res.get(k);
        	ans[k] = new int[]{a.start, a.end};
        }
        return ans;
    }
}
