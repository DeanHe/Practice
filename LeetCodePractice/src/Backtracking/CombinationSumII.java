package Backtracking;

import java.util.*;
/*Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Example
Given candidate set [10,1,6,7,2,1,5] and target 8,

A solution set is:

[
  [1,7],
  [1,2,5],
  [2,6],
  [1,1,6]
]
Notice
All numbers (including target) will be positive integers.
Elements in a combination must be in non-descending order.
The solution set must not contain duplicate combinations.
*/
public class CombinationSumII {
	/**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
	public List<List<Integer>> combinationSum2(int[] num, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (num.length == 0 || num == null) {
			return res;
		}
		List<Integer> combination = new ArrayList<>();
		Arrays.sort(num);
		dfs(num, target, res, combination, 0);
		return res;

	}

	public void dfs(int[] num, int target, List<List<Integer>> res,
			List<Integer> combination, int pos) {
		if(target < 0){
            return;
        }
        if(target == 0){
            res.add(new ArrayList<>(combination));
            return;
        }
        for(int i = pos; i < num.length; i++){
            if(i > pos && num[i] == num[i - 1]){
                continue;
            }
            combination.add(num[i]);
            dfs(num, target - num[i], res, combination, i + 1);
            combination.remove(combination.size() - 1);
        }
	}
}
