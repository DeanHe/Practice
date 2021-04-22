package binaryIndexedTree;

/*
        Given the array queries of positive integers between 1 and m, you have to process all queries[i] (from i=0 to i=queries.length-1) according to the following rules:

        In the beginning, you have the permutation P=[1,2,3,...,m].
        For the current i, find the position of queries[i] in the permutation P (indexing from 0) and then move this at the beginning of the permutation P.
        Notice that the position of queries[i] in P is the result for queries[i].
        Return an array containing the result for the given queries.

        Example 1:
        Input: queries = [3,1,2,1], m = 5
        Output: [2,1,2,1]
        Explanation: The queries are processed as follow:
        For i=0: queries[i]=3, P=[1,2,3,4,5], position of 3 in P is 2, then we move 3 to the beginning of P resulting in P=[3,1,2,4,5].
        For i=1: queries[i]=1, P=[3,1,2,4,5], position of 1 in P is 1, then we move 1 to the beginning of P resulting in P=[1,3,2,4,5].
        For i=2: queries[i]=2, P=[1,3,2,4,5], position of 2 in P is 2, then we move 2 to the beginning of P resulting in P=[2,1,3,4,5].
        For i=3: queries[i]=1, P=[2,1,3,4,5], position of 1 in P is 1, then we move 1 to the beginning of P resulting in P=[1,2,3,4,5].
        Therefore, the array containing the result is [2,1,2,1].

        Example 2:
        Input: queries = [4,1,2,2], m = 4
        Output: [3,1,2,0]

        Example 3:
        Input: queries = [7,5,5,8,3], m = 8
        Output: [6,5,0,7,5]

        Constraints:

        1 <= m <= 10^3
        1 <= queries.length <= m
        1 <= queries[i] <= m

        time complexity: O(logm), space complexity: O(m) by huahua
*/
public class QueriesOnaPermutationWithKey {
    int[] bit;

    public int[] processQueries(int[] queries, int m) {
        bit = new int[2 * m + 1];
        int[] pos = new int[m + 1];
        int[] res = new int[queries.length];
        for (int i = 1; i <= m; i++) {
            pos[i] = i + m;
            update(i + m, 1);
        }
        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            res[i] = getPreSum(pos[query] - 1); //idx = sum(bit[:pos[q]])
            update(pos[query], -1); //update bit[pos[q]] to 0
            pos[query] = m - i; // set pos[q] to m - i correspond to moving
            update(pos[query], 1); //update bit[pos[q]] to 1
        }
        return res;
    }

    private void update(int idx, int val) {
        for (int i = idx + 1; i < bit.length; i += lowbit(i)) {
            bit[i] += val;
        }
    }

    private int getPreSum(int idx) {
        int res = 0;
        for (int i = idx + 1; i > 0; i -= lowbit(i)) {
            res += bit[i];
        }
        return res;
    }

    private int lowbit(int x) {
        return x & -x;
    }
}
