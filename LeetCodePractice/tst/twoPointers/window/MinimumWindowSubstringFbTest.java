package twoPointers.window;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/*

Example 1:
Input: s = "abcdebdde", t = "bde"
Output: "bcde"
Explanation: The minimum window substring "bcde" includes 'b', 'd', and 'e' from string t.

*/
public class MinimumWindowSubstringFbTest {
    private MinimumWindowSubstringFb mw;

    @Before
    public void setup() {
        mw = new MinimumWindowSubstringFb();
    }

    @Test
    public void testCase1(){
        Assert.assertEquals("bcde", mw.minWindow("abcdebdde", "bde"));
    }

    @Test
    public void testCase2(){
        Assert.assertEquals("bcde", mw.minWindowDP("abcdebdde", "bde"));
    }
}
