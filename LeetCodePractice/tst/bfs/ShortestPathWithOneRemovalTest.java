package bfs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShortestPathWithOneRemovalTest {
    private ShortestPathWithOneRemoval sp;

    @Before
    public void setup() {
        sp = new ShortestPathWithOneRemoval();
    }

    @Test
    public void testCase1() {
        int[][] map = {
                {0, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 0, 0}, {1, 1, 1, 0}
        };
        int res = sp.shortestPath(map);
        Assert.assertEquals(7, res);
    }

    @Test
    public void testCase2() {
        int[][] map = {
                {0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}
        };
        int res = sp.shortestPath(map);
        Assert.assertEquals(11, res);
    }
}
