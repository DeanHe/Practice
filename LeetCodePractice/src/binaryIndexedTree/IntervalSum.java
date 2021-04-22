package binaryIndexedTree;

import java.util.ArrayList;
import java.util.List;

/*		Given an integer arr (index from 0 to n-1, where n is the size of this arr), and an query list. Each query has two integers [start, end]. For each query, calculate the sum number between index start and end in the given arr, return the result list.
		We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

		Example
		Example 1:
		Input: arr = [1,2,7,8,5],  queries = [(0,4),(1,2),(2,4)]
		Output: [23,9,20]

		Example 2:
		Input: arr : [4,3,1,2],  queries : [(1,2),(0,2)]
		Output: [4,8]
		Challenge
		O(logN) time for each query
*/
public class IntervalSum {
	class Interval {
		int start, end;

		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	int[] arr;
    long[] bit;
	/**
	 * @param A:
	 *            An integer list
	 * @param queries:
	 *            An query list
	 * @return: The result list
	 */
    public List<Long> intervalSum(int[] A, List<Interval> queries) {
        // write your code here
        int len = A.length;
        arr = new int[len];
        bit = new long[len + 1];
        for(int i = 0; i < len; i++){
            update(i, A[i]);
        }
        List<Long> res = new ArrayList<>();
        for(Interval q : queries){
            long temp = prefixSum(q.end) - prefixSum(q.start - 1);
            res.add(temp);
        }
        return res;
    }
    
    private long prefixSum(int idx){
        long res = 0;
        for(int i = idx + 1; i > 0; i -= lowbit(i)){
            res += bit[i];
        }
        return res;
    }
    
    private void update(int idx, int val){
        int delta = val - arr[idx];
        arr[idx] = val;
        for(int i = idx + 1; i < bit.length; i += lowbit(i)){
            bit[i] += delta;
        }
    }
    
    private int lowbit(int x){
        return x & -x;
    }
}
