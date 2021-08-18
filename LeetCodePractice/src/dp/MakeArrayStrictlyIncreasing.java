package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.

In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].

If there is no way to make arr1 strictly increasing, return -1.

 

Example 1:

Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
Output: 1
Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
Example 2:

Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
Output: 2
Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
Example 3:

Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
Output: -1
Explanation: You can't make arr1 strictly increasing.
 

Constraints:

1 <= arr1.length, arr2.length <= 2000
0 <= arr1[i], arr2[i] <= 10^9

hint:
The state would be the index in arr1 and the index of the previous element in arr2 after sorting it and removing duplicates.

analysis:
Similar to Longest Increasing Subsequence problem.
dp[i] is hashmap contains <last number : replacement count> at step i
*/
public class MakeArrayStrictlyIncreasing {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        Map<Integer, Integer> cntMap = new HashMap<>();
        cntMap.put(-1, 0);
        for (int a1 : arr1) {
            Map<Integer, Integer> temp = new HashMap<>();
            for (int n : cntMap.keySet()) {
                int n_cnt = cntMap.get(n);
                // no replace
                if (a1 > n) {
                    temp.put(a1, Math.min(temp.getOrDefault(a1, Integer.MAX_VALUE), n_cnt));
                }
                // replace a1 with next available number greater than n from arr2
                int i = binarySearch(arr2, n);
                if (i != arr2.length) {
                    temp.put(arr2[i], Math.min(temp.getOrDefault(arr2[i], Integer.MAX_VALUE), n_cnt + 1));
                }
            }
            cntMap = temp;
        }
        int res = Integer.MAX_VALUE;
        for (int n : cntMap.keySet()) {
            res = Math.min(res, cntMap.get(n));
        }
        if (res == Integer.MAX_VALUE) {
            return -1;
        }
        return res;
    }

    private int binarySearch(int[] nums, int target) {
        int s = 0, e = nums.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] <= target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return s;
    }
}
