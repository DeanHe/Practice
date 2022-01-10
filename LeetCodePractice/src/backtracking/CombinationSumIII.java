package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.


Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.

Example 2:
Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.

Example 3:
Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.


Constraints:
2 <= k <= 9
1 <= n <= 60

analysis:
TC: C(9, k) ~= (9^k),
SC: k
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
