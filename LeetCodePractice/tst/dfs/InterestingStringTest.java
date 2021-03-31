package dfs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/*
Example 1:

Input: "4g12y6hunter"
Output: true
Explanation: We can split it into "4g12y" and "6hunter".
Example 2:

Input: "124gray6hunter"
Output: true
Explanation: We can divide it into "12", "4gray", "6hunter".
Example 3:

Input: "31ba2a"
Output: false
Example 4:

Input: "1a"
Output: true
Example 5:

Input: "0"
Output: true
Example 6:

Input: ""
Output: false
Example 7:

Input: "129aaaaaaaaaaa3zyx"
Output: true
Explanation: "129aaaaaaaaaaa" + "3zyx"
 */
public class InterestingStringTest {
    private InterestingString is;

    @Before
    public void setup() {
        is = new InterestingString();
    }

    @Test
    public void testCase1(){
        Assert.assertTrue(is.check("4g12y6hunter"));
        Assert.assertTrue(is.check("124gray6hunter"));
        Assert.assertFalse(is.check("31ba2a"));
        Assert.assertTrue(is.check("1a"));
        Assert.assertTrue(is.check("0"));
        Assert.assertFalse(is.check(""));
        Assert.assertTrue(is.check("129aaaaaaaaaaa3zyx"));
    }

    @Test
    public void testCase2(){
        Assert.assertTrue(is.checkBFS("4g12y6hunter"));
        Assert.assertTrue(is.checkBFS("124gray6hunter"));
        Assert.assertFalse(is.checkBFS("31ba2a"));
        Assert.assertTrue(is.checkBFS("1a"));
        Assert.assertTrue(is.checkBFS("0"));
        Assert.assertFalse(is.checkBFS(""));
        Assert.assertTrue(is.checkBFS("129aaaaaaaaaaa3zyx"));
    }
}
