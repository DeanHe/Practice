/*Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array, find the sum of the element inside the window at each moving.*/
public class WindowSum {
	/**
     * @param nums a list of integers.
     * @return the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        if(nums == null || nums.length < k || k <= 0){
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i < k; i++){
            res[0] += nums[i];
        }
        for(int i = 1; i + k - 1 < nums.length; i++){
            res[i] = res[i - 1] - nums[i - 1] + nums[i + k - 1];
        }
        return res;
    }
}
