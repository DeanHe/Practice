package dp;

public class MaximumSubarrayDifference {
	/**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two
     *          Subarrays
     */
    public int maxDiffSubArrays(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        
        int[] leftMax = new int[len];
        int[] leftMin = new int[len];
        leftMax[0] = nums[0];
        leftMin[0] = nums[0];
        int minSum = nums[0];
        int maxSum = nums[0];
        for(int i = 1; i < len; i++){
            maxSum = Math.max(maxSum + nums[i], nums[i]);
            leftMax[i] = Math.max(leftMax[i - 1], maxSum);
            
            minSum = Math.min(minSum + nums[i], nums[i]);
            leftMin[i] = Math.min(leftMin[i - 1], minSum);
        }
        int[] rightMax = new int[len];
        int[] rightMin = new int[len];
        rightMax[len - 1] = nums[len - 1];
        rightMin[len - 1] = nums[len - 1];
        minSum = nums[len - 1];
        maxSum = nums[len - 1];
        for(int i = len - 2; i >= 0; i--){
            maxSum = Math.max(nums[i], nums[i] + maxSum);
            rightMax[i] = Math.max(rightMax[i + 1], maxSum);
            
            minSum = Math.min(nums[i], nums[i] + minSum);
            rightMin[i] = Math.min(rightMin[i + 1], minSum);
        }
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < len - 1; i++){
            if(res < Math.abs(leftMax[i] - rightMin[i + 1])){
                res = Math.abs(leftMax[i] - rightMin[i + 1]);
            }
            if(res < Math.abs(leftMin[i] - rightMax[i + 1])){
                res = Math.abs(leftMin[i] - rightMax[i + 1]);
            }
        }
        return res;
    }
}
