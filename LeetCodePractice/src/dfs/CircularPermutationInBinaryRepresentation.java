package dfs;

/*
Given 2 integers n and start. Your task is return any permutation p of (0,1,2.....,2^n -1) such that :

        p[0] = start
        p[i] and p[i+1] differ by only one bit in their binary representation.
        p[0] and p[2^n -1] must also differ by only one bit in their binary representation.


        Example 1:

        Input: n = 2, start = 3
        Output: [3,2,0,1]
        Explanation: The binary representation of the permutation is (11,10,00,01).
        All the adjacent element differ by one bit. Another valid permutation is [3,1,0,2]
        Example 2:

        Input: n = 3, start = 2
        Output: [2,6,7,5,4,0,1,3]
        Explanation: The binary representation of the permutation is (010,110,111,101,100,000,001,011).


        Constraints:

        1 <= n <= 16
        0 <= start < 2 ^ n

tag:bit manipulation
*/

import java.util.ArrayList;
import java.util.List;

public class CircularPermutationInBinaryRepresentation {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[1 << n];
        dfs(res, visited, start, n);
        return res;
    }

    private void dfs(List<Integer> res, boolean[] visited, int cur, int n) {
        if (visited[cur]) {
            return;
        }
        res.add(cur);
        visited[cur] = true;
        for (int i = 0; i < n; i++) {
            int bit = 1 << i;
            int neighbor = cur ^ bit; // flip bit
            dfs(res, visited, neighbor, n);
        }
    }
}
