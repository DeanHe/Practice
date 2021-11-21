package string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MinimumStringLengthAfterTwoDeletionTest {
    private MinimumStringLengthAfterTwoDeletion ms;

    @Before
    public void setup() {
        ms = new MinimumStringLengthAfterTwoDeletion();
    }

    @Test
    public void testCase1(){
        int res = ms.minimumLengthAfterDeletion("aabcccbb");
        Assert.assertEquals(2, res);
    }

    @Test
    public void testCase2(){
        int res = ms.minimumLengthAfterDeletion("aabaa");
        Assert.assertEquals(0, res);
    }

    @Test
    public void testCase3(){
        int res = ms.minimumLengthAfterDeletion("abbcdeeefg");
        Assert.assertEquals(5, res);
    }

    @Test
    public void testCase4(){
        int res = ms.minimumLengthAfterDeletion("aabcccbba");
        Assert.assertEquals(3, res);
    }
}
