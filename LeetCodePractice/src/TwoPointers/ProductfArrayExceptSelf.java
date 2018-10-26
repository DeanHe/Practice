package TwoPointers;

public class ProductfArrayExceptSelf {
	/**
     * @param nums: an array of integers
     * @return: the product of all the elements of nums except nums[i].
     */
	public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        for(int i = 1; i < n; i++){
            left[i] = nums[i-1]*left[i-1];
        }
        right[n-1] = 1;
        for(int i = n -2; i >= 0; i--){
            right[i] = nums[i+1]*right[i+1];
        }
        for(int i = 0; i < n; i++){
            res[i] = left[i]*right[i];
        }
        return res;
    }
}
