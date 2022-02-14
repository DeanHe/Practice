package stack.parenthese;

import org.junit.Before;
import org.junit.Test;
import string.MergeIpSubset;

import java.util.List;

public class MinimumRemoveToMakeValidParenthesesFBTest {
    private MinimumRemoveToMakeValidParenthesesFB mr;

    @Before
    public void setup() {
        mr = new MinimumRemoveToMakeValidParenthesesFB();
    }

    @Test
    public void testCase1() {
        String str = mr.minRemoveToMakeValid("abc({d}ef])hig");
        System.out.println(str);
    }

    @Test
    public void testCase2() {
        String str = mr.minRemoveToMakeValid("[]}{)()){[})");
        System.out.println(str);
    }
}
