package backtracking;
/*
Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.
Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/
import java.util.*;

public class Subsets {
	/**
     * @param nums: A set of distinct integers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return res;
        }
        Arrays.sort(nums);
        ArrayList<Integer> temp = new ArrayList<>();
        helper(res, nums, temp, 0);
        return res;
    }
    private void helper(ArrayList<ArrayList<Integer>> res, int[] nums, ArrayList<Integer> temp, int pos){
        res.add(new ArrayList<>(temp));
        for(int i = pos; i < nums.length; i++){
            temp.add(nums[i]);
            helper(res, nums, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
