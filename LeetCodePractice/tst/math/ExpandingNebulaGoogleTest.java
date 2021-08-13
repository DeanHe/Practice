package math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExpandingNebulaGoogleTest {
    private ExpandingNebulaGoogle en;

    @Before
    public void setup() {
        en = new ExpandingNebulaGoogle();
    }

    @Test
    public void testCase1() {
        boolean[][] g = {
                {true, false, true},
                {false, true, false},
                {true, false, true}
        };
        int res = en.solution(g);
        Assert.assertEquals(4, res);
    }

    @Test
    public void testCase2() {
        boolean[][] g = {
                {true, false, true, false, false, true, true, true},
                {true, false, true, false, false, false, true, false},
                {true, true, true, false, false, false, true, false},
                {true, false, true, false, false, false, true, false},
                {true, false, true, false, false, true, true, true}
        };
        int res = en.solution(g);
        Assert.assertEquals(254, res);
    }

    @Test
    public void testCase3() {
        boolean[][] g = {
                {true, true, false, true, false, true, false, true, true, false},
                {true, true, false, false, false, false, true, true, true, false},
                {true, true, false, false, false, false, false, false, false, true},
                {false, true, false, false, false, false, true, true, false, false}
        };
        int res = en.solution(g);
        Assert.assertEquals(11567, res);
    }
}
