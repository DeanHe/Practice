package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(k, n, 1, res, new ArrayList<>());
        return res;
    }

    private void dfs(int k, int target, int pos, List<List<Integer>> res, List<Integer> ls) {
        if (k < 0 || target < 0) {
            return;
        }
        if (target == 0 && k == 0) {
            res.add(new ArrayList<>(ls));
            return;
        }
        for (int i = pos; i <= 9; i++) {
            ls.add(i);
            dfs(k - 1, target - i, i + 1, res, ls);
            ls.remove(ls.size() - 1);
        }
    }
}
