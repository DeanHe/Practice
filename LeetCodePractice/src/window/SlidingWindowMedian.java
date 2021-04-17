package window;

import java.util.PriorityQueue;

/*Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note: 
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
*/
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
