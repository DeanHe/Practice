package Heap;

import java.util.*;
/*
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

Example
Example 1:

Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Notice
the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
*/
public class SmallestRange {
	public int[] smallestRange(List<List<Integer>> nums) {
        int rows = nums.size();
        int[] next = new int[rows];
        int rangeLow = 0, rangeHigh = Integer.MAX_VALUE, localMin = 0, localMax =Integer.MIN_VALUE;
        // min queue store list index order
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(rows, (i, j) -> nums.get(i).get(next[i]) - nums.get(j).get(next[j]));
        for(int r = 0; r < rows; r++){
            minQueue.offer(r);
            localMax = Math.max(localMax, nums.get(r).get(0));
        }
        while(!minQueue.isEmpty()){
        	// move is the minimum element pointer of 'next' array
            int listIdx = minQueue.poll();
            localMin = nums.get(listIdx).get(next[listIdx]);
            next[listIdx]++;
            if((localMax - localMin) < (rangeHigh - rangeLow)){
                rangeHigh = localMax;
                rangeLow = localMin;
            }
            if(next[listIdx] == nums.get(listIdx).size()){
                break;
            }
            minQueue.offer(listIdx);
            localMax = Math.max(localMax, nums.get(listIdx).get(next[listIdx])); 
        }
        return new int[] {rangeLow, rangeHigh};
    }
}
