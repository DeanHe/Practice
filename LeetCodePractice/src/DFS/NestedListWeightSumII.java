package DFS;

import Stack.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example
Example 1:

Input: nestedList = [[1,1],2,[1,1]]
Output: 8
Explanation:
four 1's at depth 1, one 2 at depth 2
Example 2:

Input: nestedList = [1,[4,[6]]]
Output: 17
Explanation:
one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17
 */
public class NestedListWeightSumII {
    /**
     * @param nestedList: a list of NestedInteger
     * @return: the sum
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }
        int res = 0, preSum = 0;
        Queue<NestedInteger> q = new LinkedList<>(nestedList);
        while (!q.isEmpty()) {
            int len = q.size();
            int levelSum = 0;
            for (int i = 0; i < len; i++) {
                NestedInteger cur = q.poll();
                if (cur.isInteger()) {
                    levelSum += cur.getInteger();
                } else {
                    q.addAll(cur.getList());
                }
            }
            preSum += levelSum;
            res += preSum;
        }
        return res;
    }
}
