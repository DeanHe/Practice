package Array;

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
        int len = nums.length;
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < len; i++){
            int idx = Math.abs(nums[i]) - 1;
            if(nums[idx] < 0){
                res.add(Math.abs(idx + 1));
            }
            nums[idx] = - nums[idx];
            System.out.println(Arrays.toString(nums));
        }
        return res;
    }
}
