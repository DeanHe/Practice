package dp;

import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. For each day, you can choose to buy stock or sell all stock or hold.

Example 1:
Given an example [2,1,2,0,1], return 2

Example 2:
Input: [3,3,5,0,0,3,1,4], return 16

Example 3:
Input: [1,2,3,4,5]
Output: 10

Example 4:
Input: [5,4,3,2,1]
Output: 0

Example 5
Given prices = [4,4,6,1,1,5,2,1],
Output: 12

Notice
You may not engage in multiple transactions at the same time.

 */
public class BestTimeToBuyAndSellStockFacebookTest {
    private BestTimeToBuyAndSellStockFacebook bt;

    @Before
    public void setup() {
        bt = new BestTimeToBuyAndSellStockFacebook();
    }

    @Test
    public void testCase1() {
        int[] prices = {2, 1, 2, 0, 1};
        Assert.assertEquals(2, bt.sellStock(prices));
    }

    @Test
    public void testCase2() {
        int[] prices = {1, 2, 3, 4, 5};
        Assert.assertEquals(10, bt.sellStock(prices));
    }

    @Test
    public void testCase3() {
        int[] prices = {5, 4, 3, 2, 1};
        Assert.assertEquals(0, bt.sellStock(prices));
    }

    @Test
    public void testCase4() {
        int[] prices = {4, 4, 6, 1, 1, 5, 2, 1};
        Assert.assertEquals(12, bt.sellStock(prices));
    }

    @Test
    public void testCase5() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        Assert.assertEquals(16, bt.sellStock(prices));
    }
}
