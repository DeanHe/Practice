package monotonicQueue;
/*
Given an array of n integer with duplicate number, and a moving window(size k), move the window at each iteration from the start of the array, find the maximum number inside the window at each moving.

Example
For array [1, 2, 7, 7, 8], moving window size k = 3. return [7, 7, 8]

At first the window is at the start of the array like this
[|1, 2, 7| ,7, 8] , return the maximum 7;

then the window move one step forward.
[1, |2, 7 ,7|, 8], return the maximum 7;

then the window move one step forward again.
[1, 2, |7, 7, 8|], return the maximum 8;

Challenge
o(n) time and O(k) memory

Analysis:
need to maintain a deque which is decreasing order

template:
1 poll from first, making deque window is valid
2 poll from last, making deque monotonic
3 offer index on last
3 peek result from first
*/
import java.util.*;

public class SlidingWindowMaximum {
	/**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++){
            int startIdx = i - k + 1;
            while(!deque.isEmpty() && i - deque.peekFirst() >= k){
                deque.pollFirst();
            }
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if(startIdx >= 0){
                res[startIdx] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
