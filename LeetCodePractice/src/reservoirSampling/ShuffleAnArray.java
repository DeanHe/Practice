package reservoirSampling;

import java.util.Random;

/*
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

analysis:
Fisher–Yates shuffle
*/
public class ShuffleAnArray {
	private int[] nums;
	private Random random;
 	public ShuffleAnArray(int[] nums){
		this.nums = nums;
		random = new Random();
	}
	
	/** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
    	if(nums == null){
    		return null;
    	}
    	int[] res = nums.clone();
        int len = res.length;
        for(int i = 0; i < len; i++){
        	int index = randRange(i, len);
        	swap(res, i, index);
        }
        return res;
    }
    
    private int randRange(int min, int max){
    	return random.nextInt(max - min) + min;
    }

    private void swap(int[] arr, int i, int j){
    	int temp = arr[i];
    	arr[i] = arr[j];
    	arr[j] = temp;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
