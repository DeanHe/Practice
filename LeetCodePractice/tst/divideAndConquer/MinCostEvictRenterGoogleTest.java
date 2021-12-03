package divideAndConquer;

import dfs.FindPathApple;
import dividAndConquer.MinCostEvictRenterGoogle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MinCostEvictRenterGoogleTest {
    private MinCostEvictRenterGoogle mc;

    @Before
    public void setup() {
        mc = new MinCostEvictRenterGoogle();
    }

    @Test
    public void testCase1(){
        int[] houses = {1, 1, 1, 1, 1, 1, 1};
        int[] renters = {4, 2, 3};
        int cost = mc.minCost(houses, renters);
        Assert.assertTrue(cost > 0);
    }
}
