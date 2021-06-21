package dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/*
Given a binary array consisting of only zeroes and ones. The task is to find:


The number of subarrays which has only 1 in it.
The number of subarrays which has only 0 in it.
Examples:


Input: arr[] = {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1}
Output:
The number of subarrays consisting of 0 only: 7
The number of subarrays consisting of 1 only: 7

Input: arr[] = {1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1}
Output:
The number of subarrays consisting of 0 only: 5
The number of subarrays consisting of 1 only: 15

 */
public class CountSubarraysWithAllOnesTest {
    private CountSubarraysWIthAllOnes cs;

    @Before
    public void setup() {
        cs = new CountSubarraysWIthAllOnes();
    }

    @Test
    public void testCase1() {
        int[] arr = {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1};
        Assert.assertEquals(7, cs.count(arr));
    }

    @Test
    public void testCase2() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1};
        Assert.assertEquals(15, cs.count(arr));
    }
}
