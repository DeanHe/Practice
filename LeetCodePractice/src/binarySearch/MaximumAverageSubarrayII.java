package binarySearch;

/*
Given an array with positive and negative numbers, find the maximum average subarray which length should be greater or equal to given length k.

Example
Example 1:

Input:
[1,12,-5,-6,50,3]
3
Output:
15.667
Explanation:
 (-6 + 50 + 3) / 3 = 15.667
Example 2:

Input:
[5]
1
Output:
5.000
Notice
It's guaranteed that the size of the array is greater or equal to k.
*/
public class MaximumAverageSubarrayII {
	/**
	 * @param nums:
	 *            an array
	 * @param k:
	 *            an integer
	 * @return: the maximum average value
	 */
	public double maxAverage(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k > nums.length) {
			throw new IllegalArgumentException("Invalid input");
		}
		double start = (double) Integer.MAX_VALUE;
		double end = (double) Integer.MIN_VALUE;
		for (int num : nums) {
			start = Math.min(start, num);
			end = Math.max(end, num);
		}
		while (end - start > 1e-6d) {
			double mid = (start + end) / 2;
			if (existsAverage(mid, nums, k)) {
				start = mid;
			} else {
				end = mid;
			}
		}
		return end;
	}
	
	/*
     *  check if there is a subarray of nums, whose:
     *  1. count of elements >= k;
     *  2. average >= mid;
     */
	private boolean existsAverage(double mid, int[] nums, int k) {
		int len = nums.length;
		double[] preSum = new double[len + 1];
		double minSum = 0.0;
		for(int i = 1; i <= len; i++){
			/*
             *  Normalize all the numbers by substracting mid,
             *  so the requirement become: check if there is a
             *  subarray of nums, whose count of elements >= k
             *  and sum >= 0.
             *  Generate its pre-sum array so that we can quickly
             *  calculate the sum of a certain range of nums.
            */
			preSum[i] = preSum[i - 1] + nums[i - 1] - mid;
			if(i >= k){
				minSum = Math.min(minSum, preSum[i - k]);
				//if first
				if(preSum[i] >= minSum){
					return true;
				}
			}
		}
		return false;
	}
}
