package Backtracking;

import java.util.*;

public class Permutations {
	/**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null){
            return res;
        }
        if(nums.length == 0){
            res.add(new ArrayList<Integer>());
        	return res;
        }
        int len = nums.length;
        boolean[] visited = new boolean[len];
        List<Integer> temp = new ArrayList<>();
        dfs(res, temp, nums, visited);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] visited){
    	if(temp.size() == nums.length){
    		res.add(new ArrayList<Integer>(temp));
    	}
    	for(int i = 0; i < nums.length; i++){
    		if(!visited[i]){
    			visited[i] = true;
    			temp.add(nums[i]);
    			dfs(res, temp, nums, visited);
    			visited[i] = false;
    			temp.remove(temp.size() - 1);
    		}
    	}
    }
}
