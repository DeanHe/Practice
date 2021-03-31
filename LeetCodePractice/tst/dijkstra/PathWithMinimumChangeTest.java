package dijkstra;

import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

public class PathWithMinimumChangeTest {
    private PathWithMinimumChange pw;

    @Before
    public void setup() {
        pw = new PathWithMinimumChange();
    }

    @Test
    public void testCase1() {
        int[][] matrix = {
                {1, 3, 5},
                {6, 4, 2},
                {4, 8, 1}
        };
        int res = pw.minCostToMakePath(matrix);
        Assert.assertEquals(4, res);
    }
}
