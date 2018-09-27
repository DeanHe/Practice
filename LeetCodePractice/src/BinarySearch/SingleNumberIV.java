package BinarySearch;
/*Give an array, all the numbers appear twice except one number which appears once and all the numbers which appear twice are next to each other. Find the number which appears once.*/
// https://www.lintcode.com/problem/single-number-iv/description
public class SingleNumberIV {
	/**
     * @param nums: The number array
     * @return: Return the single number
     */
    public int getSingleNumber(int[] nums) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while(start < end){
        	int mid = start + (end - start) / 2;
            if(nums[mid] == nums[mid - 1]){
                if((mid - start + 1) % 2 == 1){
                    end = mid - 2;
                } else {
                    start = mid + 1;
                }
            } else if(nums[mid] == nums[mid + 1]){
                if((end - mid + 1) % 2 == 1){
                    start = mid + 2;
                } else {
                    end = mid - 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[start];
    }
}
