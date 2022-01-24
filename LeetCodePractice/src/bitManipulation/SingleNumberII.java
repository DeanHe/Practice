package bitManipulation;
/*
Given 3*n + 1 non-negative integer, every number occurs triple times except one, find it.
Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:
	Input:  [1,1,2,3,3,3,2,2,4,1]
	Output:  4

Example 2:
	Input: [2,1,2,2]
	Output:  1

Constraints:
1 <= nums.length <= 3 * 10^4
-231 <= nums[i] <= 2^31 - 1
Each element in nums appears exactly three times except for one element which appears once.

Challenge
One-pass, constant extra space.
*/
public class SingleNumberII {
	public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for(int n : nums) {
                if(((n >> i) & 1) == 1){
                    sum++;
                    sum %= 3;
                }
            }
            if(sum != 0){
                res |= sum << i;
            }

        }
        return res;
    }
}
