package array;

import java.util.ArrayList;
import java.util.List;

/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements of [1, n] inclusive that do not appear in this array.
Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example 1:
Input:[4,3,2,7,8,2,3,1]
Output:[5,6]

Example 2:
Input: nums = [1,1]
Output: [2]

Constraints:

n == nums.length
1 <= n <= 10^5
1 <= nums[i] <= n

Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

hint:
1 This is a really easy problem if you decide to use additional memory. For those trying to write an initial solution using additional memory, think counters!
2 However, the trick really is to not use any additional space than what is already available to use. Sometimes, multiple passes over the input array help find the solution. However, there's an interesting piece of information in this problem that makes it easy to re-use the input array itself for the solution.
3 The problem specifies that the numbers in the array will be in the range [1, n] where n is the number of elements in the array. Can we use this information and modify the array in-place somehow to find what we need?

analysis:
convert value in array to index of array, flip the visited index position.
as there must be duplicate number in array
The missing number correspondent index will be flipped, we can find it be picking out the positive number from modified array
*/
public class FindAllNumbersDisappearedInAnArray {
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
        	int idx = Math.abs(nums[i]) - 1;
        	if(nums[idx] > 0){
        		nums[idx] = -nums[idx];
        	}
        }
        for(int i = 0; i < nums.length; i++){
        	if(nums[i] > 0){
        		res.add(i + 1);
        	}
        }
        return res;
    }
}
