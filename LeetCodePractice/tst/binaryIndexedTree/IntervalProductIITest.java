package binaryIndexedTree;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/*
		Given an integer array in the construct method, implement two methods query(start, end) and modify(index, value):
		For query(start, end), return the sum from index start to index end in the given array.
		For modify(index, value), modify the number in the given index to value
		We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

		Example
		Example 1
		Input:
		[1,2,7,8,5]
		[query(0,2),modify(0,4),query(0,1),modify(2,1),query(2,4)]
		Output: [10,6,14]
		Explanation:
		Given array A = [1,2,7,8,5].
		After query(0, 2), 1 * 2 * 7 = 14,
		After modify(0, 4), change A[0] from 1 to 4, A = [4,2,7,8,5].
		After query(0, 1), 4 * 2 = 8.
		After modify(2, 1), change A[2] from 7 to 5，A = [4,2,5,8,5].
		After query(2, 4), 5 * 8 * 5 = 200.

		Example 2
		Input:
		[1,2,3,4,5]
		[query(0,0),query(1,2),quert(3,4)]
		Output: [1,5,9]
		Explantion:
		1 = 1
		2 * 3 = 6
		4 * 5 = 20
		Challenge
		O(logN) time for query and modify.
*/
public class IntervalProductIITest {
    private IntervalProductII ip;

    @Before
    public void setup() {
        int[] A = {1, 2, 7, 8, 5};
        ip = new IntervalProductII(A);
    }

    @Test
    public void testCase1() {
        int res = ip.query(0, 2);
        Assert.assertEquals(14, res);
        ip.modify(0, 4, 1);
        res = ip.query(0, 1);
        Assert.assertEquals(8, res);
        ip.modify(2, 5, 7);
        res = ip.query(2, 4);
        Assert.assertEquals(200, res);
    }
}
