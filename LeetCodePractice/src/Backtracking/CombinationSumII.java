package Backtracking;

import java.util.*;

public class CombinationSumII {
	public List<List<Integer>> combinationSum2(int[] num, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (num.length == 0 || num == null) {
			return res;
		}
		List<Integer> combination = new ArrayList<Integer>();
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
