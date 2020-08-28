package DFS;

import java.util.ArrayList;
import java.util.List;

/*
Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.

You may return the answer in any order.



Example 1:

Input: N = 3, K = 7
Output: [181,292,707,818,929]
Explanation: Note that 070 is not a valid number, because it has leading zeroes.
Example 2:

Input: N = 2, K = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]


Note:

1 <= N <= 9
0 <= K <= 9
 */
public class NumbersWithSameConsecutiveDifferences {
    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> res = new ArrayList<>();
        if(N == 1){
            res.add(0);
        }
        dfs(res, 0, N, K);
        return res.stream().mapToInt(i -> i).toArray();
    }

    private void dfs(List<Integer> res, int cur, int n, int k) {
        if(n == 0){
            res.add(cur);
            return;
        }
        for(int d = 0; d <= 9; d++){
            if(cur == 0){
                if(d != 0){
                    dfs(res, d, n - 1, k);
                }
            } else {
                int lastDigit = cur % 10;
                if(Math.abs(lastDigit - d) == k){
                    dfs(res, cur * 10 + d, n - 1, k);
                }
            }
        }
    }
}
