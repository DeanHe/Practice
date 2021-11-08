package array;
/*
Given an unsorted integer array, find the first missing positive integer.

Example
Example 1:

Input:[1,2,0]
Output:3
Example 2:

Input:[3,4,-1,1]
Output:2
Challenge
Your algorithm should run in O(n) time and uses constant space.

hint:
1 Think about how you would solve the problem in non-constant space. Can you apply that logic to the existing space?
2 We don't care about duplicates or non-positive integers
3 Remember that O(2n) = O(n)

analysis:
value to index mapping
*/
public class FirstMissingPositive {
	public int firstMissingPositive(int[] nums) {
		if(nums == null || nums.length == 0){
            return 1;
        }
        int n = nums.length;
        for(int i = 0; i < n; i++){
            while(nums[i] - 1 >= 0 && nums[i] - 1 < n && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }
        }
        for(int i = 0; i < n; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return n + 1;
	}

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
