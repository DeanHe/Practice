package dp.TimeSequenceType;

import dp.CountSubarraysWIthAllOnes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1

 */
public class LongestIncreasingSubsequenceTest {
    private LongestIncreasingSubsequence lis;

    @Before
    public void setup() {
        lis = new LongestIncreasingSubsequence();
    }

    @Test
    public void testCase1() {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        Assert.assertEquals(4, lis.longestIncreasingSubsequenceII(arr));
    }

    @Test
    public void testCase2() {
        int[] arr = {0, 1, 0, 3, 2, 3};
        Assert.assertEquals(4, lis.longestIncreasingSubsequenceII(arr));
    }

    @Test
    public void testCase3() {
        int[] arr = {7, 7, 7, 7, 7, 7, 7};
        Assert.assertEquals(1, lis.longestIncreasingSubsequenceII(arr));
    }
}
