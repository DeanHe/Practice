package dfs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FindPathAppleTest {
    private FindPathApple fp;

    @Before
    public void setup() {
        fp = new FindPathApple();
    }

    @Test
    public void testCase1(){
        int[][] matrix = {
                {0, 1, 1, 0},
                {1, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 1}
        };
        int[] start = {1, 1};
        int[] end = {3, 3};
        List<int[]> path = fp.findPath(matrix, start, end);
        int[] last = path.get(path.size() - 1);
        Assert.assertTrue(end[0] == last[0] && end[1] == last[1]);
        Assert.assertTrue(path.size() > 0);
    }
}
