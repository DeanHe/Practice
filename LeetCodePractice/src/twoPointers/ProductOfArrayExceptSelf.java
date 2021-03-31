package twoPointers;

/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
*/
public class ProductOfArrayExceptSelf {
    /**
     * @param nums: an array of integers
     * @return: the product of all the elements of nums except nums[i].
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int[] pre = new int[len];
        int[] suffix = new int[len];
        pre[0] = 1;
        for (int i = 1; i < len; i++) {
            pre[i] = nums[i - 1] * pre[i - 1];
        }
        suffix[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            suffix[i] = nums[i + 1] * suffix[i + 1];
        }
        for (int i = 0; i < len; i++) {
            res[i] = pre[i] * suffix[i];
        }
        return res;
    }
}
