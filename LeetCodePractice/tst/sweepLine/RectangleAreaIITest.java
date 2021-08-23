package sweepLine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
Example 1:


Input: rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
Output: 6
Explanation: As illustrated in the picture.
Example 2:

Input: rectangles = [[0,0,1000000000,1000000000]]
Output: 49
Explanation: The answer is 1018 modulo (109 + 7), which is (109)2 = (-7)2 = 49.
 */
public class RectangleAreaIITest {
    private RectangleAreaII ra;

    @Before
    public void setup() {
        ra = new RectangleAreaII();
    }

    @Test
    public void testCase1() {
        int[][] rectangles = {
                {0, 0, 2, 2},
                {1, 0, 2, 3},
                {1, 0, 3, 1}
        };
        int res = ra.rectangleArea(rectangles);
        Assert.assertEquals(6, res);
    }

    @Test
    public void testCase2() {
        int[][] rectangles = {
                {0, 0, 1000000000, 1000000000}
        };
        int res = ra.rectangleArea(rectangles);
        Assert.assertEquals(49, res);
    }

    @Test
    public void testCase3() {
        int[][] rectangles = {
                {0, 0, 1, 1},
                {2, 2, 3, 3}
        };
        int res = ra.rectangleArea(rectangles);
        Assert.assertEquals(2, res);
    }

    @Test
    public void testCase4() {
        int[][] rectangles = {
                {0, 0, 2, 2},
                {1, 0, 3, 1}
        };
        int res = ra.rectangleArea(rectangles);
        Assert.assertEquals(5, res);
    }

    @Test
    public void testCase5() {
        int[][] rectangles = {
                {49, 40, 62, 100},
                {11, 83, 31, 99},
                {19, 39, 30, 99}
        };
        int res = ra.rectangleArea(rectangles);
        Assert.assertEquals(1584, res);
    }

    @Test
    public void testCase6() {
        int[][] rectangles = {
                {0, 0, 2, 2},
                {4, 0, 6, 2}
        };
        int res = ra.rectangleArea(rectangles);
        Assert.assertEquals(8, res);
    }
}
