package Backtracking;

import java.util.*;

public class SubsetsII {
	/**
     * @param nums: A set of integers that might contain duplicates.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(nums == null || nums.length == 0){
            return res;
        }
        Arrays.sort(nums);
        List<Integer> temp = new ArrayList<>();
        helper(res, nums, temp, 0);
        return res;
        
    }
    private void helper(ArrayList<ArrayList<Integer>> res, int[] nums, List<Integer> temp, int pos){
        res.add(new ArrayList<Integer>(temp));
        int len = nums.length;
        for(int i = pos; i < len; i++){
            if(i > pos && nums[i] == nums[i - 1]){
                continue;
            }
            temp.add(nums[i]);
            helper(res, nums, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
