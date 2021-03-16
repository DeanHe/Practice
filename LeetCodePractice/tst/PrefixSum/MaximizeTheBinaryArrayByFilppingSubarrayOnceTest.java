package PrefixSum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MaximizeTheBinaryArrayByFilppingSubarrayOnceTest {
    private MaximizeTheBinaryArrayByFilppingSubarrayOnce mt;

    @Before
    public void setup() {
        mt = new MaximizeTheBinaryArrayByFilppingSubarrayOnce();
    }

    @Test
    public void testCase1() {
        int[] arr = {1, 0, 1, 0, 0, 1, 0, 1};
        Assert.assertEquals(6, mt.solve(arr));
    }

    @Test
    public void testCase2() {
        int[] arr = {1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1};
        Assert.assertEquals(10, mt.solve(arr));
    }

}
