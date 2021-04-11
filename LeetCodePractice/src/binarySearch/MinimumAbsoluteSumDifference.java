package binarySearch;

import java.util.Arrays;

/*
You are given two positive integer arrays nums1 and nums2, both of length n.

The absolute sum difference of arrays nums1 and nums2 is defined as the sum of |nums1[i] - nums2[i]| for each 0 <= i < n (0-indexed).

You can replace at most one element of nums1 with any other element in nums1 to minimize the absolute sum difference.

Return the minimum absolute sum difference after replacing at most one element in the array nums1. Since the answer may be large, return it modulo 109 + 7.

|x| is defined as:

x if x >= 0, or
-x if x < 0.


Example 1:

Input: nums1 = [1,7,5], nums2 = [2,3,5]
Output: 3
Explanation: There are two possible optimal solutions:
- Replace the second element with the first: [1,7,5] => [1,1,5], or
- Replace the second element with the third: [1,7,5] => [1,5,5].
Both will yield an absolute sum difference of |1-2| + (|1-3| or |5-3|) + |5-5| = 3.
Example 2:

Input: nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
Output: 0
Explanation: nums1 is equal to nums2 so no replacement is needed. This will result in an
absolute sum difference of 0.
Example 3:

Input: nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
Output: 20
Explanation: Replace the first element with the second: [1,10,4,4,2,7] => [10,10,4,4,2,7].
This yields an absolute sum difference of |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20


Constraints:

n == nums1.length
n == nums2.length
1 <= n <= 10^5
1 <= nums1[i], nums2[i] <= 10^5
 */
public class MinimumAbsoluteSumDifference {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int MOD = (int)(1e9 + 7);
        int len = nums1.length;
        int[] sort1 = nums1.clone();
        Arrays.sort(sort1);
        int diffSum = 0;
        for(int i = 0; i < len; i++){
            diffSum = (diffSum + Math.abs(nums1[i] - nums2[i])) % MOD;
        }
        if(diffSum == 0){
            return 0;
        }
        int save = 0;
        for(int i = 0; i < len; i++){
            if(nums1[i] == nums2[i]){
                continue;
            }
            int idx = Arrays.binarySearch(sort1, nums2[i]);
            int originalDiff = Math.abs(nums1[i] - nums2[i]);
            if(idx >= 0){
                save = Math.max(save, originalDiff); // remove this pair from diff
            } else {
                idx = -idx - 1;
                if(idx - 1 > 0){
                    save = Math.max(save, originalDiff - Math.abs(sort1[idx - 1] - nums2[i]));
                }
                if(idx < len){
                    save = Math.max(save, originalDiff - Math.abs(sort1[idx] - nums2[i]));
                }
            }
        }
        return (diffSum - save) % MOD;
    }
}

