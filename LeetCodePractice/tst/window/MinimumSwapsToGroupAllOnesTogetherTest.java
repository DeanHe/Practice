package window;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/*
/*
Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.

Example 1:

Input: [1,0,1,0,1]
Output: 1
Explanation:
There are 3 ways to group all 1's together:
[1,1,1,0,0] using 1 swap.
[0,1,1,1,0] using 2 swaps.
[0,0,1,1,1] using 1 swap.
The minimum is 1.
Example 2:

Input: [0,0,0,1,0]
Output: 0
Explanation:
Since there is only one 1 in the array, no swaps needed.
Example 3:

Input: [1,0,1,0,1,0,0,1,1,0,1]
Output: 3
Explanation:
One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
Note:

1 <= data.length <= 10^5
0 <= data[i] <= 1

analysis:
TC O(N)
 */
public class MinimumSwapsToGroupAllOnesTogetherTest {
    private MinimumSwapsToGroupAllOnesTogether ms;

    @Before
    public void setup() {
        ms = new MinimumSwapsToGroupAllOnesTogether();
    }

    @Test
    public void testCase1() {
        int[] arr = {1, 0, 1, 0, 1};
        int res = ms.minSwaps(arr);
        Assert.assertEquals(1, res);
    }

    @Test
    public void testCase2() {
        int[] arr = {1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1};
        int res = ms.minSwaps(arr);
        Assert.assertEquals(3, res);
    }

    @Test
    public void testCase3() {
        int[] arr = {0, 0, 0, 1, 0};
        int res = ms.minSwaps(arr);
        Assert.assertEquals(0, res);
    }
}

