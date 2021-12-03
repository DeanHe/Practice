package backtracking;
/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

Time Complexity: O(n!)
*/
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
            res.add(new ArrayList<>());
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
    		res.add(new ArrayList<>(temp));
    		return;
    	}
    	for(int i = 0; i < nums.length; i++){
    		if(!visited[i]){
    			visited[i] = true;
    			temp.add(nums[i]);
    			dfs(res, temp, nums, visited);
    			temp.remove(temp.size() - 1);
                visited[i] = false;
    		}
    	}
    }
}
