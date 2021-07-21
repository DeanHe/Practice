package bfs;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class SlidingPuzzlePrintPathTest {
    private SlidingPuzzlePrintPath ed;
    @Before
    public void setup() {
        ed = new SlidingPuzzlePrintPath();
    }

    @Test
    public void testCase1(){
        int[][] start = {
                {2, 0, 3},
                {1, 8, 4},
                {7, 6, 5},
            };
        int[][] end = {
                {1, 2, 3},
                {8, 0, 4},
                {7, 6, 5},
        };
        String path = ed.findPath(start, end);
        System.out.println(path);
        Assert.assertTrue(!path.isEmpty());
    }
}
