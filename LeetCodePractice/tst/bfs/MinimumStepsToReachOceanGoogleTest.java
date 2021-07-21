package bfs;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class MinimumStepsToReachOceanGoogleTest {
    private MinimumStepsToReachOceanGoogle ms;
    @Before
    public void setup() {
        ms = new MinimumStepsToReachOceanGoogle();
    }

    @Test
    public void testCase1() {
        char[][] matrix = {
                {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'L', 'L', 'L', 'L', 'O', 'O'},
                {'O', 'L', 'W', 'W', 'L', 'O', 'O'},
                {'O', 'L', 'L', 'L', 'L', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
        };
        int res = ms.minStepsToDig(matrix);
        Assert.assertEquals(1, res);
    }

    @Test
    public void testCase2(){
        char[][] matrix = {
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'L', 'L', 'L', 'L', 'O', 'O', 'O', 'L', 'O', 'O'},
                {'O', 'L', 'W', 'W', 'L', 'O', 'L', 'L', 'W', 'L', 'O'},
                {'O', 'L', 'L', 'L', 'L', 'O', 'O', 'L', 'W', 'L', 'O'},
                {'O', 'L', 'L', 'L', 'L', 'O', 'O', 'O', 'L', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
        };
        int res = ms.minStepsToDig(matrix);
        Assert.assertEquals(2, res);
    }
}
