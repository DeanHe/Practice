package monotonicQueue;

import PrefixSum.MaximizeTheBinaryArrayByFilppingSubarrayOnce;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaintFenceGoogleTest {
    private PaintFenceGoogle pf;

    @Before
    public void setup() {
        pf = new PaintFenceGoogle();
    }

    @Test
    public void testCase1() {
        int[] fence = {1, 3, 1, 2, 3, 1, 2, 1};
        Assert.assertEquals(6, pf.minPaint(fence));
    }

    @Test
    public void testCase2() {
        int[] fence = {1, 3, 2, 3, 3, 2, 2, 1};
        Assert.assertEquals(4, pf.minPaint(fence));
    }

    @Test
    public void testCase3() {
        int[] fence = {1, 2, 1, 2, 1};
        Assert.assertEquals(3, pf.minPaint(fence));
    }

}
