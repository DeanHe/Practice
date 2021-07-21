package bfs;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class MinimumDistanceOfTwoCharacter2DTest {
    private MinimumDistanceOfTwoCharacter2D md;
    @Before
    public void setup() {
        md = new MinimumDistanceOfTwoCharacter2D();
    }

    @Test
    public void testCase1() throws Exception {
        char[][] matrix = {
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'a'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'},
            };
        int res = md.calculateMinimumDistance2D(matrix, 'a', 'm');
        Assert.assertEquals(3, res);
    }
}
