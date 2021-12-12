package array;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
/*
Given two integer arrays of same size, nums and indexes, reorder elements in nums according to given indexes array.

Example 1:
Input: nums = [24, 56, 74, -23, 87, 91], indexes = [1, 2, 3, 0, 4, 5]
Output: [-23, 24, 56, 74, 87, 91]

Example 2:
Input: nums = [10, 11, 12], indexes = [1, 0, 2]
Output: [11, 10, 12]

Expected O(n) time and O(1) space solution.
 */

public class ReorderArrayAccordingToTheGivenIndexesTest {
    private ReorderArrayAccordingToTheGivenIndexes ra;
    @Before
    public void setup() {
        ra = new ReorderArrayAccordingToTheGivenIndexes();
    }

    @Test
    public void testCase1(){
        int[] nums = {24, 56, 74, -23, 87, 91};
        int[] indexes = {1, 2, 3, 0, 4, 5};
        ra.reorder(nums, indexes);
        System.out.println(Arrays.toString(nums));
    }
}
