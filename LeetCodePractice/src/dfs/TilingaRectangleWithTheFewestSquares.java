package dfs;

import java.util.HashMap;
import java.util.Map;

/*
Given a rectangle of size n x m, return the minimum number of integer-sided squares that tile the rectangle.

Example 1:
Input: n = 2, m = 3
Output: 3
Explanation: 3 squares are necessary to cover the rectangle.
2 (squares of 1x1)
1 (square of 2x2)

Example 2:
Input: n = 5, m = 8
Output: 5

Example 3:
Input: n = 11, m = 13
Output: 6

Constraints:
1 <= n, m <= 13

hint:
1 Can you use backtracking to solve this problem ?.
2 Suppose you've placed a bunch of squares. Where is the natural spot to place the next square ?.
3 The maximum number of squares to be placed will be ≤ max(n,m).
 */
public class TilingaRectangleWithTheFewestSquares {
    Map<Long, Integer> mem = new HashMap<>();
    int res = Integer.MAX_VALUE;

    public int tilingRectangle(int rows, int cols) {
        if (rows == cols) {
            return 1;
        }
        if (rows > cols) {
            return tilingRectangle(cols, rows);
        }
        dfs(new int[rows], 0, rows, cols);
        return res;
    }

    private void dfs(int[] h, int cnt, int rows, int cols) {
        if (cnt >= res) {
            return;
        }
        int minIdx = -1, minH = Integer.MAX_VALUE;
        long hash = 0, base = 1;
        for (int i = 0; i < rows; i++) {
            hash += h[i] * base;
            base *= cols + 1;
            if (h[i] < minH) {
                minH = h[i];
                minIdx = i;
            }
        }
        if (minH == cols) {
            res = Math.min(res, cnt);
            return;
        }
        if (mem.containsKey(hash) && mem.get(hash) <= cnt) {
            return;
        }
        mem.put(hash, cnt);
        int end = minIdx;
        while (end + 1 < rows && h[end + 1] == minH && (end + 1 - minIdx + 1 + minH) <= cols) {
            end++;
        }
        for (int i = end; i >= minIdx; i--) {
            int[] next = h.clone();
            for (int j = minIdx; j <= i; j++) {
                next[j] += i - minIdx + 1; //newly added square edge length
            }
            dfs(next, cnt + 1, rows, cols);
        }
    }
}
