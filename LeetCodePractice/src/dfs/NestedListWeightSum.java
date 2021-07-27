package dfs;

import stack.NestedInteger;

import java.util.List;

/*
#339
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example

Example 1:
Input: the list [[1,1],2,[1,1]],
Output: 10.
Explanation:
four 1's at depth 2, one 2 at depth 1, 4 * 1 * 2 + 1 * 2 * 1 = 10

Example 2:
Input: the list [1,[4,[6]]],
Output: 27.
Explanation:
one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4 * 2 + 6 * 3 = 27
 */
public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int res = 0;
        if (nestedList == null || nestedList.isEmpty()) {
            return res;
        }
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) {
                res += depth * n.getInteger();
            } else {
                List<NestedInteger> ls = n.getList();
                res += dfs(ls, depth + 1);
            }
        }
        return res;
    }
}
