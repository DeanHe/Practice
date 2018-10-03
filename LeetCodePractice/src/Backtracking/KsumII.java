package Backtracking;

import java.util.*;

public class KsumII {
	public List<List<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(A, 0, k, target, res, temp);
        return res;
    }
    
    private void dfs(int[] A, int i, int k, int target, List<List<Integer>> res, List<Integer> temp){
        if(target == 0 && k == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int j = i; j < A.length; j++){
            temp.add(A[j]);
            dfs(A, j + 1, k - 1, target - A[j], res, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
