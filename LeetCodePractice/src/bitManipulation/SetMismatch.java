package bitManipulation;

/*
You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

You are given an integer array nums representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return them in the form of an array.



Example 1:

Input: nums = [1,2,2,4]
Output: [2,3]
Example 2:

Input: nums = [1,1]
Output: [1,2]


Constraints:

2 <= nums.length <= 10^4
1 <= nums[i] <= 10^4

analysis:
value to index, as the value is 1 to n for n slot
TC O(N)
SC O(1)
 */
public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        int len = nums.length;
        for (int n : nums) {
            int idx = Math.abs(n) - 1;
            if (nums[idx] > 0) {
                nums[idx] = -nums[idx];
            } else {
                //found duplicate element
                res[0] = idx + 1;
            }
        }
        // At this point, only nums[missingNumber-1] > 0
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                res[1] = i + 1;
            } else {
                res[i] = -res[i]; // restore array
            }
        }
        return res;
    }
}
