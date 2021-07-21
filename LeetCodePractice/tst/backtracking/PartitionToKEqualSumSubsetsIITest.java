package backtracking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


import java.util.List;

/*
Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 */

public class PartitionToKEqualSumSubsetsIITest {
    private PartitionToKEqualSumSubsetsII pt;
    @Before
    public void setup() {
        pt = new PartitionToKEqualSumSubsetsII();
    }

    @Test
    public void testCase1(){
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        List<List<Integer>> res = pt.canPartitionKSubsets(nums, k);
        for(List<Integer> ls : res){
            System.out.println(ls);
        }
        Assert.assertTrue(res.size() > 0);
    }
}
