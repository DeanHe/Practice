package hashMap;

import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

public class DotProductOfVectorWithRepeatValuesTest {
    private DotProductOfVectorsWithRepeatValues dp;

    @Before
    public void setup() {
        dp = new DotProductOfVectorsWithRepeatValues();
    }

    @Test
    public void testCase1() {
        int[] vector1 = {1, 1, 1, 2, 2, 2, 3, 3, 3};
        int[] vector2 = {1, 1, 1, 2, 2, 2, 3, 3, 3};
        int res = dp.doProduct(vector1, vector2);
        Assert.assertEquals(42, res);
    }

    @Test
    public void testCase2() {
        int[] vector1 = {1, 1, 1, 2, 2, 2, 3, 3, 3};
        int[] vector2 = {1, 1, 1, 1, 2, 2, 3, 3, 3};
        int res = dp.doProduct(vector1, vector2);
        Assert.assertEquals(40, res);
    }
}
