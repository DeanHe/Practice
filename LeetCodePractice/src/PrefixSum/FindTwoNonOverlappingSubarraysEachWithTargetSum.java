package PrefixSum;

/*
Given an array of integers arr and an integer target.

You have to find two non-overlapping sub-arrays of arr each with sum equal target. There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.

Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.

Example 1:
Input: arr = [3,2,2,4,3], target = 3
Output: 2
Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.

Example 2:
Input: arr = [7,3,4,7], target = 7
Output: 2
Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7], [3,4] and [7]), but we will choose the first and third sub-arrays as the sum of their lengths is 2.

Example 3:
Input: arr = [4,3,2,6,2,3,4], target = 6
Output: -1
Explanation: We have only one sub-array of sum = 6.

Example 4:
Input: arr = [5,5,4,4,5], target = 3
Output: -1
Explanation: We cannot find a sub-array of sum = 3.

Example 5:
Input: arr = [3,1,1,1,5,1,2,1], target = 3
Output: 3
Explanation: Note that sub-arrays [1,2] and [2,1] cannot be an answer because they overlap.

Constraints:
1 <= arr.length <= 10^5
1 <= arr[i] <= 1000
1 <= target <= 10^8

analysis:
time complexity O(n)
*/

import java.util.HashMap;
import java.util.Map;

public class FindTwoNonOverlappingSubarraysEachWithTargetSum {
    public int minSumOfLengths(int[] arr, int target) {
        int len = arr.length;
        int res = Integer.MAX_VALUE;
        Map<Integer, Integer> preSumIdx = new HashMap<>();
        int[] minLen = new int[len]; //minlen[i] means the min length of subarray with sum equals target in arr[:i]
        preSumIdx.put(0, -1);
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                minLen[i] = Integer.MAX_VALUE;
            } else {
                minLen[i] = minLen[i - 1];
            }
            sum += arr[i];
            if (preSumIdx.containsKey(sum - target)) {
                int start = preSumIdx.get(sum - target);
                minLen[i] = Math.min(minLen[i], i - start);
                if (start != -1 && minLen[start] != Integer.MAX_VALUE) { // indicates find two subarrays now
                    res = Math.min(res, minLen[start] + i - start);
                }
            }
            preSumIdx.put(sum, i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
