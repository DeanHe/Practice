package PrefixSum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/*
Example 1:

Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
Output: [3,0,3]
Explanation:
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).
Example 2:

Input: colors = [1,2], queries = [[0,3]]
Output: [-1]
Explanation: There is no 3 in the array.
 */
public class ShortestDistanceToTargetColorTest {
    private ShortestDistanceToTargetColor sd;

    @Before
    public void setup() {
        sd = new ShortestDistanceToTargetColor();
    }

    @Test
    public void testCase1() {
        int[] colors = {1, 1, 2, 1, 3, 2, 2, 3, 3};
        int[][] queries = {{1, 3}, {2, 2}, {6, 1}};
        Assert.assertTrue(Arrays.asList(3, 0, 3).equals(sd.shortestDistanceColor(colors, queries)));
        Assert.assertTrue(Arrays.asList(3, 0, 3).equals(sd.shortestDistanceColorDP(colors, queries)));
    }

    @Test
    public void testCase2() {
        int[] colors = {1, 2};
        int[][] queries = {{0, 3}};
        Assert.assertTrue(Arrays.asList(-1).equals(sd.shortestDistanceColor(colors, queries)));
        Assert.assertTrue(Arrays.asList(-1).equals(sd.shortestDistanceColorDP(colors, queries)));
    }
}
