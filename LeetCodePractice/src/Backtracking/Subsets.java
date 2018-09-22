package Backtracking;

import java.util.*;

public class Subsets {
	/**
     * @param S: A set of distinct integers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(nums == null || nums.length == 0){
            return res;
        }
        Arrays.sort(nums);
        ArrayList<Integer> temp = new ArrayList<Integer>();
        helper(res, nums, temp, 0);
        return res;
    }
    private void helper(ArrayList<ArrayList<Integer>> res, int[] nums, ArrayList<Integer> temp, int pos){
        res.add(new ArrayList<Integer>(temp));
        for(int i = pos; i < nums.length; i++){
            temp.add(nums[i]);
            helper(res, nums, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
