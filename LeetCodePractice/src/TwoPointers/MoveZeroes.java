package TwoPointers;
/*Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]*/
public class MoveZeroes {
	public void moveZeroes(int[] nums) {
        int index = 0;
        int i = 0;
        while(i < nums.length){
            if(nums[i] == 0){
                i++;
            } else {
                nums[index] = nums[i];
                i++;
                index++;
            }
        }
        while(index < nums.length){
            nums[index] = 0;
            index++;
        }
    }
}
