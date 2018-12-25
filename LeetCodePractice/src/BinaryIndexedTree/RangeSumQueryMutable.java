package BinaryIndexedTree;
/*Description
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
1.The array is only modifiable by the update function.
2.You may assume the number of calls to update and sumRange function is distributed evenly.

Example
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8*/
public class RangeSumQueryMutable {
	
	class NumArray {
		private int[] arr, bit;
		
	    public NumArray(int[] nums) {
	    	int len = nums.length;
	    	arr = new int[len];
	    	bit = new int[len + 1];
	    	for(int i = 0; i < len; i++){
	    		update(i, nums[i]);
	    	}
	    }
	    
	    public void update(int index, int val) {
	       int delta = val - arr[index];
	       arr[index] = val;
	       
	       for(int i = index + 1; i <= arr.length; i += lowbit(i)){
	    	   bit[i] += delta;
	       }
	    }
	    
	    public int sumRange(int left, int right) {
	        return getPrefixSum(right) - getPrefixSum(left - 1);
	    }
	    
	    private int getPrefixSum(int index) {
	    	int sum = 0;
	    	for(int i = index + 1; i > 0; i -= lowbit(i)){
	    		sum += bit[i];
	    	}
	    	return sum;
	    }
	    
	    private int lowbit(int x){
	    	return x & (-x);
	    }
	}

}
