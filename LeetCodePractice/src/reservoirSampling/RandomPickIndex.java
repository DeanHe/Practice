package reservoirSampling;

import java.util.Random;

/*
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
*/
public class RandomPickIndex {
	Random random;
	int[] nums;
    public RandomPickIndex(int[] nums) {
    	random = new Random();
    	this.nums = nums;
    }
    
    public int pick(int target) {
    	int len = nums.length;
    	int pos = 0, count = 1, probability;
        for(int i = 0; i < len; i++){
        	if(target == nums[i]){
        		probability = random.nextInt(count);
        		if(probability == 0){
        			pos = i;
        		}
        		count++;
        	}
        }
        return pos;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */