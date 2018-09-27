package Backtracking;

import java.util.*;

public class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0){
            return res;
        }
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
