package PrefixSum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MaximizeTheBinaryMatrixByFilppingSubmatrixOnceTest {
    private MaximizeTheBinaryMatrixByFilppingSubmatrixOnce mt;

    @Before
    public void setup() {
        mt = new MaximizeTheBinaryMatrixByFilppingSubmatrixOnce();
    }

    @Test
    public void testCase1(){
        int[][] mat = {
                { 0, 0, 1 },
                { 0, 0, 1 },
                { 1, 0, 1 }
        };
        Assert.assertEquals(8, mt.solve(mat));
    }
}
