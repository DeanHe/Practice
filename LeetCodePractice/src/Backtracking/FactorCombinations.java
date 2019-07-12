package Backtracking;

import java.util.*;

/*Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
  
  Input : 16
Output :2 2 2 2 
        2 2 4 
        2 8 
        4 4 

Input : 12
Output : 2 2 3
         2 6
         3 4
Write a function that takes an integer n and return all possible combinations of its factors.
Note:
You may assume that n is always positive.
Factors should be greater than 1 and less than n.*/
public class FactorCombinations {
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> ls = new ArrayList<>();
		dfs(res, ls, n, 2, 1);
		return res;
	}
	private void dfs(List<List<Integer>> res, List<Integer> ls, int target, int start, int product){
		if(product == target){
			res.add(new ArrayList<>(ls));
			return;
		}
		for(int i = start; i < target; i++){
			if(i * product > target){
				break;
			}
			if(target % i == 0){
				ls.add(i);
				dfs(res, ls, target, i, i * product);
				ls.remove(ls.size() - 1);
			}
		}
	}
}
