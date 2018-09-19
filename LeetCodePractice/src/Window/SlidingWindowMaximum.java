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
        Deque<Integer> dequeue = new ArrayDeque<>();
        if(nums.length == 0){
            return res;
        }
        for(int i = 0; i < k - 1; i++){
            inQueue(dequeue, nums[i]);
        }
        for(int i = k -1; i < nums.length; i++){
            inQueue(dequeue, nums[i]);
            res.add(dequeue.peekFirst());
            outQueue(dequeue, nums[i - k + 1]);
        }
        return res;
    }
    
    private void inQueue(Deque<Integer> dequeue, int val){
        while(!dequeue.isEmpty() && dequeue.peekLast() < val){
            dequeue.pollLast();
        }
        dequeue.offer(val);
    }
    
    private void outQueue(Deque<Integer> dequeue, int val){
        if(dequeue.peekFirst() == val){
            dequeue.pollFirst();
        }
    }
}
