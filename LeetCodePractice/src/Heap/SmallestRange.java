package Heap;

import java.util.*;

public class SmallestRange {
	public int[] smallestRange(List<List<Integer>> nums) {
        int rows = nums.size();
        int[] next = new int[rows];
        int rangeLow = 0, rangeHigh = Integer.MAX_VALUE, localMin = 0, localMax =Integer.MIN_VALUE;
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(rows, (i, j) -> nums.get(i).get(next[i]) - nums.get(j).get(next[j]));
        for(int r = 0; r < rows; r++){
            minQueue.offer(r);
            localMax = Math.max(localMax, nums.get(r).get(0));
        }
        while(!minQueue.isEmpty()){
        	// move is the minimum element pointer of 'next' array
            int move = minQueue.poll();
            localMin = nums.get(move).get(next[move]);
            next[move]++;
            if((localMax - localMin) < (rangeHigh - rangeLow)){
                rangeHigh = localMax;
                rangeLow = localMin;
            }
            if(next[move] == nums.get(move).size()){
                break;
            }
            minQueue.offer(move);
            localMax = Math.max(localMax, nums.get(move).get(next[move])); 
        }
        return new int[] {rangeLow, rangeHigh};
    }
}
