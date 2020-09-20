package Window;

/*
Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array, find the sum of the element inside the window at each moving
.*/
public class WindowSum {
	/**
     * @param nums a list of integers.
     * @return the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        if(nums == null || nums.length < k || k <= 0){
            return new int[0];
        }
        int windowSum = 0;
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++){
        	windowSum += nums[i];
        	if(i >= k - 1){
        		res[i - k + 1] = windowSum;
        		windowSum -= nums[i - k + 1];
        	}
        }
        return res;
    }
}
