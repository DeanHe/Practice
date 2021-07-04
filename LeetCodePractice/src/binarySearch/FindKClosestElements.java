package binarySearch;

import java.util.ArrayList;
import java.util.List;

/*
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b


Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]


Constraints:

1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 10^4


analysis:
approach 1: binary search for mid point TC: O(log(N−k)+k)
approach 2: two pointers O(N) for two side bound
 */
public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int s = 0, e = arr.length - k;
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        for (int i = s; i < s + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    public List<Integer> findClosestElementsII(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int s = 0, e = arr.length - 1;
        while (e - s >= k) {
            if (arr[e] - x >= x - arr[s]) {
                e--;
            } else {
                s++;
            }
        }
        for (int i = s; i <= e; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}

