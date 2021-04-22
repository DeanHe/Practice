package greedy;
/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
*/

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {
    /**
     * @param nums: A list of integers
     * @return: The majority numbers that occurs more than 1/3
     */
    public List<Integer> majorityElement(int[] nums) {
        // write your code
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int count1 = 0;
        int count2 = 0;
        int candidate1 = -1;
        int candidate2 = -1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (count1 == 0) {
                candidate1 = nums[i];
                count1 = 1;
            } else if (candidate1 == nums[i]) {
                count1++;
            } else if (count2 == 0) {
                candidate2 = nums[i];
                count2 = 1;
            } else if (candidate2 == nums[i]) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (candidate1 == nums[i]) {
                count1++;
            } else if (candidate2 == nums[i]) {
                count2++;
            }
        }
        if (count1 > len / 3) {
            res.add(candidate1);
        }
        if (count2 > len / 3) {
            res.add(candidate2);
        }
        return res;
    }
}
