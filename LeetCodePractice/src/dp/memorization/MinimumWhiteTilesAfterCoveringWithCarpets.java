package dp.memorization;
/*
You are given a 0-indexed binary string floor, which represents the colors of tiles on a floor:

floor[i] = '0' denotes that the ith tile of the floor is colored black.
On the other hand, floor[i] = '1' denotes that the ith tile of the floor is colored white.
You are also given numCarpets and carpetLen. You have numCarpets black carpets, each of length carpetLen tiles. Cover the tiles with the given carpets such that the number of white tiles still visible is minimum. Carpets may overlap one another.

Return the minimum number of white tiles still visible.



Example 1:


Input: floor = "10110101", numCarpets = 2, carpetLen = 2
Output: 2
Explanation:
The figure above shows one way of covering the tiles with the carpets such that only 2 white tiles are visible.
No other way of covering the tiles with the carpets can leave less than 2 white tiles visible.
Example 2:


Input: floor = "11111", numCarpets = 2, carpetLen = 3
Output: 0
Explanation:
The figure above shows one way of covering the tiles with the carpets such that no white tiles are visible.
Note that the carpets are able to overlap one another.


Constraints:
1 <= carpetLen <= floor.length <= 1000
floor[i] is either '0' or '1'.
1 <= numCarpets <= 1000

hint:
1 Can you think of a DP solution?
2 Let DP[i][j] denote the minimum number of white tiles still visible from indices i to floor.length-1 after covering with at most j carpets.
3 The transition will be whether to put down the carpet at position i (if possible), or not.

TC O(N*K)
SC O(N*K)

similar to backpack, cover or skip the current tile position
 */
public class MinimumWhiteTilesAfterCoveringWithCarpets {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int fLen = floor.length();
        Integer[][] mem = new Integer[fLen + 1][numCarpets + 1];
        return dfs(mem, floor, fLen, numCarpets, carpetLen);
    }

    private int dfs(Integer[][] mem, String floor, int fLen, int numCarpets, int carpetLen) {
        if(fLen <= 0){
            return mem[fLen][numCarpets] = 0;
        }
        if(mem[fLen][numCarpets] != null){
            return mem[fLen][numCarpets];
        }
        if(fLen <= numCarpets * carpetLen){
            return mem[fLen][numCarpets] = 0;
        }
        int res = dfs(mem, floor, fLen - 1, numCarpets, carpetLen) + floor.charAt(fLen - 1) - '0';
        if(numCarpets > 0){
            res = Math.min(res, dfs(mem, floor, fLen - carpetLen, numCarpets - 1, carpetLen));
        }
        return mem[fLen][numCarpets] = res;
    }
}
