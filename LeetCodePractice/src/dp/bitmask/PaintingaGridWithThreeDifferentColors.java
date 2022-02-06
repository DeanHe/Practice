package dp.bitmask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each cell red, green, or blue. All cells must be painted.
Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can be very large, return it modulo 109 + 7.

Example 1:
Input: m = 1, n = 1
Output: 3
Explanation: The three possible colorings are shown in the image above.

Example 2:
Input: m = 1, n = 2
Output: 6
Explanation: The six possible colorings are shown in the image above.

Example 3:
Input: m = 5, n = 5
Output: 580986

Constraints:
1 <= m <= 5
1 <= n <= 1000

hint:
1 Represent each colored column by a bitmask based on each cell color.
2 Use bitmasks DP with state (currentCell, prevColumn).

analysis:
similar to NumberOfWaysToPaintNby3Grid
use 2 bits to represent a color {1, 2, 3}
use bitmask to represent previous column state, and current column state
2 layer of dfs

Try colors c range [1, 2, 3]

TC: O(N * 3^M * 2^M), where N refers to columns, M refers to rows
SC: O(N * 4^M)
 */
public class PaintingaGridWithThreeDifferentColors {
    int MOD = (int)(1e9 + 7);
    Integer[][] mem;
    int rows, cols;
    List<Integer> colors;
    public int colorTheGrid(int m, int n) {
        this.rows = m;
        this.cols = n;
        colors = Arrays.asList(1, 2, 3);
        mem = new Integer[1 << (2 * rows)][cols + 1];
        return dfs(0, 0, mem);
    }

    private int dfs(int preColMask, int c, Integer[][] mem) {
        if(c == cols){
            return 1;
        }
        if(mem[preColMask][c] != null){
            return mem[preColMask][c];
        }
        int res = 0;
        for(int nb : next(preColMask)){
            res = (res + dfs(nb, c + 1, mem)) % MOD;
        }
        return mem[preColMask][c] = res;
    }

    private List<Integer> next(int mask) {
        List<Integer> res = new ArrayList<>();
        helper(mask, 0, 0, res);
        return res;
    }

    private void helper(int preColMask, int curColMask, int r, List<Integer> res) {
        if(r == rows){
            res.add(curColMask);
            return;
        }
        for(int color : colors){
            if(getColor(preColMask, r) != color && (r == 0 || getColor(curColMask, r -1) != color)){
                helper(preColMask, setColor(curColMask, color, r), r + 1, res);
            }
        }
    }

    private int setColor(int mask, int color, int pos){
        return mask | (color << (2 * pos));
    }

    //Get color of the `mask` at `pos`, 2 bit store 1 color
    private int getColor(int mask, int pos){
        return (mask >> (2 * pos)) & 3;
    }
}

