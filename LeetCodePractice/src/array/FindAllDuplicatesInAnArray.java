package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
 */
public class FindAllDuplicatesInAnArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int n : nums){
            int idx = Math.abs(n) - 1;
            if(nums[idx] < 0){
                res.add(idx + 1);
            }
            nums[idx] = -nums[idx];
        }
        return res;
    }
}
