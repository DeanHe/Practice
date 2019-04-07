package Backtracking;

import java.util.*;
/*Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Example
Given candidate set [2,3,6,7] and target 7, a solution set is:

[7]
[2, 2, 3]
Notice
All numbers (including target) will be positive integers.
Elements in a combination must be in non-descending order.
The solution set must not contain duplicate combinations.*/
public class CombinationSum {
	/**
     * @param candidates: A list of integers
     * @param target: An integer
     * @return: A list of lists of integers
     */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0){
            return res;
        }
        Arrays.sort(candidates);
        ArrayList<Integer> temp = new ArrayList<>();
        dfs(candidates, target, 0, temp, res);
        return res;
    }
    private void dfs(int[] candidates, int target, int pos, ArrayList<Integer> temp, List<List<Integer>> res){
        if(target < 0){
            return;
        }
        if(target == 0){
            res.add(new ArrayList<Integer>(temp));
        }
        for(int i = pos; i < candidates.length; i++){
            if(i > pos && candidates[i] == candidates[i - 1]){
                continue;
            }
            temp.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, temp, res);
            temp.remove(temp.size() - 1);
        }
    }
}
