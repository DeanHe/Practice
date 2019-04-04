package Window;
/*Given an array of n integer with duplicate number, and a moving window(size k), move the window at each iteration from the start of the array, find the maximum number inside the window at each moving.

Example
For array [1, 2, 7, 7, 8], moving window size k = 3. return [7, 7, 8]

At first the window is at the start of the array like this
[|1, 2, 7| ,7, 8] , return the maximum 7;

then the window move one step forward.
[1, |2, 7 ,7|, 8], return the maximum 7;

then the window move one step forward again.
[1, 2, |7, 7, 8|], return the maximum 8;

Challenge
o(n) time and O(k) memory*/
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
