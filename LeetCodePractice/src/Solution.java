import java.util.*;

import BST.NNode;
import BST.SerializeAndDeserializeNaryTree;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution();
	}
}

public class SlidingWindowMedian {
	PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
	public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0){
        	return new double[0];
        }
        int resLen = nums.length - k + 1;
        double[] res = new double[resLen];
        for(int i = 0; i < nums.length; i++){
        	add(nums[i]);
        	if(i >= k - 1){
        		res[i - k + 1] = getMedian();
        		remove(nums[i - k + 1]);
        	}
        }
        return res;
    }
	
	private void add(int num){
		maxHeap.offer(num);
		minHeap.offer(maxHeap.poll());
		if(minHeap.size() > maxHeap.size()){
			maxHeap.offer(minHeap.poll());
		}
	}
	
	private void remove(int num){
		if(maxHeap.remove(num)){
			
		} else {
			minHeap.remove(num);
		}
		if(minHeap.size() > maxHeap.size()){
			maxHeap.offer(minHeap.poll());
		} else if(maxHeap.size() - minHeap.size() > 1){
			minHeap.offer(maxHeap.poll());
		}
	}
	
	private double getMedian(){
		if(minHeap.size() == 0 && maxHeap.size() == 0){
			return 0;
		}
		if(minHeap.size() == maxHeap.size()){
			return ((double)minHeap.peek() + (double)maxHeap.peek()) / 2.0;
		} else {
			return (double)maxHeap.peek();
		}
	}
}
