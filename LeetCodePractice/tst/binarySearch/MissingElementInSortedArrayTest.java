package binarySearch;

import array.ReorderArrayAccordingToTheGivenIndexes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
/*
Example 1:
Input: A = [4,7,9,10], K = 1
Output: 5
Explanation:
The first missing number is 5.

Example 2:
Input: A = [4,7,9,10], K = 3
Output: 8
Explanation:
The missing numbers are [5,6,8,…], hence the third missing number is 8.

Example 3:
Input: A = [1,2,4], K = 3
Output: 6
Explanation:
The missing numbers are [3,5,6,7,…], hence the third missing number is 6.
 */

public class MissingElementInSortedArrayTest {
    private MissingElementInSortedArray me;

    @Before
    public void setup() {
        me = new MissingElementInSortedArray();
    }

    @Test
    public void testCase1() {
        int[] nums = {4, 7, 9, 10};
        int res = me.missingElement(nums, 1);
        Assert.assertEquals(5, res);
    }

    @Test
    public void testCase2() {
        int[] nums = {4, 7, 9, 10};
        int res = me.missingElement(nums, 3);
        Assert.assertEquals(8, res);
    }

    @Test
    public void testCase3() {
        int[] nums = {1, 2, 4};
        int res = me.missingElement(nums, 3);
        Assert.assertEquals(6, res);
    }
}
