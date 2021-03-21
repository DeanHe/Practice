package backtracking;
/*Given n unique integers, number k (1<=k<=n) and target.

Find all possible k integers where their sum is target.

Example
Example 1:
	Input: [1,2,3,4], k = 2, target = 5
	Output:  [[1,4],[2,3]]

Example 2:
	Input: [1,3,4,6], k = 3, target = 8
	Output:  [[1,3,4]]*/
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
