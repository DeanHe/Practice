package dp.bitmask;

import dp.memorization.MaxScoreOfMatrixGoogle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
/*
There are houses laid out on a NxN grid.
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

This solution is optimized to about O(N*φ^N) for an NxN matrix
 */
public class HouseRobber2DTest {
    private HouseRobber2D hr;

    @Before
    public void setup() {
        hr = new HouseRobber2D();
    }

    @Test
    public void testCase1(){
        int[][] matrix = {
                {10, 20, 10},
                {20, 40, 20},
                {10, 20, 10},
        };
        int res = hr.rob(matrix);
        Assert.assertEquals(80, res);
    }
}
