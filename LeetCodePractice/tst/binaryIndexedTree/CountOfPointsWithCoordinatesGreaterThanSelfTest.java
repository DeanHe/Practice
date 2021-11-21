package binaryIndexedTree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/*
Example:

Input: (4,3),(5,1),(2,4),(1,0),(3,2)
output: [0, 0, 0, 4, 1]
sort by x descending y increasing (5,1),(4,3),(3,2),(2,4),(1,0)
then apply <count of greater number before itself on Y>
*/
public class CountOfPointsWithCoordinatesGreaterThanSelfTest {
    private CountOfPointsWithCoordinatesGreaterThanSelf co;

    @Before
    public void setup() {
        co = new CountOfPointsWithCoordinatesGreaterThanSelf();
    }

    @Test
    public void testCase1() {
        int[][] points = {{4,3}, {5,1}, {2,4}, {1,0}, {3,2}};
        int[] res = co.countGreaterPoints(points);
        Assert.assertArrayEquals(new int[]{0, 0, 0, 4, 1}, res);
    }

    @Test
    public void testCase2() {
        int[][] points = {{10,100}, {5,2}, {40,400}, {9, 50}, {30,20}};
        int[] res = co.countGreaterPoints(points);
        Assert.assertArrayEquals(new int[]{1, 4, 0, 2, 1}, res);
    }
}
