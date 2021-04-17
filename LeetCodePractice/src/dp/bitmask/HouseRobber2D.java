package dp.bitmask;

import java.util.ArrayList;
import java.util.List;

/*
There are houses laid out on a NxM grid.
Each house is known to contain some amount of valuables.
The robbers task is to rob as many houses as possible to maximize the amount of loot.
However there is a security system in place and if you rob two adjacent houses (to the left, right, above and below) an alarm will go off.
Find the maximum loot the robber can rob.

Find the optimum set of non-adjacent houses to rob.
    e.g.:  Houses:  alignment:
          10 20 10     0  1  0
          20 30 20 =>  1  0  1
          10 20 10     0  1  0
        This alignment results in the maximum of 80.

This solution is optimized to about O(N*2^M) for an NxM matrix
 */
public class HouseRobber2D {
    int rows, cols;
    Integer[][] mem;
    public int rob(int[][] nums) {
        rows = nums.length;
        cols = nums[0].length;
        mem = new Integer[rows][1 << cols];
        return getMax(nums, 0, 0);
    }

    private int getMax(int[][] nums, int r, int state) {
        if(r == rows){
            return 0;
        }
        if(mem[r][state] != null){
            return mem[r][state];
        }
        List<Integer> neighbors = new ArrayList<>();
        dfs(neighbors, state,0, 0);
        int res = 0;
        for(int nb : neighbors){
            res = Math.max(res, sum(nums, r, nb) + getMax(nums, r + 1, nb));
        }
        return mem[r][state] = res;
    }

    private void dfs(List<Integer> res, int preState, int c, int cur) {
        if(c == cols){
            res.add(cur);
            return;
        }
        dfs(res, preState, c + 1, cur);
        if((preState & (1 << c)) == 0
                && (c == 0 || (cur & (1 << (c - 1))) == 0)
                && (c == cols - 1 || (cur & (1 << (c + 1))) == 0)
        ){
            dfs(res, preState, c + 1, cur | (1 << c));
        }
    }

    private int sum(int[][] nums, int r, int state){
        int res = 0;
        for(int c = 0; c < cols; c++){
            if((state & (1 << c)) == (1 << c)){
                res += nums[r][c];
            }
        }
        return res;
    }
}

