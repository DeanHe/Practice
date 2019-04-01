package Window;

import java.util.*;

public class SlidingWindowMaximum {
	/**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        // write your code here
        //need to maintain a deque which is decreasing order
        ArrayList<Integer> res = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        if(nums.length == 0){
            return res;
        }
        for(int i = 0; i < k - 1; i++){
            inQueue(deque, nums[i]);
        }
        for(int i = k -1; i < nums.length; i++){
            inQueue(deque, nums[i]);
            res.add(deque.peekFirst());
            outQueue(deque, nums[i - k + 1]);
        }
        return res;
    }
    
    private void inQueue(Deque<Integer> deque, int val){
        while(!deque.isEmpty() && deque.peekLast() < val){
            deque.pollLast();
        }
        deque.offer(val);
    }
    
    private void outQueue(Deque<Integer> deque, int val){
        if(deque.peekFirst() == val){
            deque.pollFirst();
        }
    }
}
