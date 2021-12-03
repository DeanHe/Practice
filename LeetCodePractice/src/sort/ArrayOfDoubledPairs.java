package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an integer array of even length arr,
return true if it is possible to reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.

Example 1:
Input: arr = [3,1,3,6]
Output: false

Example 2:
Input: arr = [2,1,2,6]
Output: false

Example 3:
Input: arr = [4,-2,2,-4]
Output: true
Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].

Example 4:
Input: arr = [1,2,4,16,8,4]
Output: false

Constraints:
2 <= arr.length <= 3 * 10^4
arr.length is even.
-10^5 <= arr[i] <= 10^5
 */
public class ArrayOfDoubledPairs {
    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int n : arr) {
            cnt.put(n, cnt.getOrDefault(n, 0) + 1);
        }
        for (int a : arr) {
            if (cnt.get(a) == 0) {
                continue;
            }
            if (a < 0 && a % 2 != 0) {
                return false;
            }
            int b;
            if (a >= 0) {
                b = a * 2;
            } else {
                b = a / 2;
            }
            if (cnt.getOrDefault(b, 0) == 0) {
                return false;
            }
            cnt.put(a, cnt.get(a) - 1);
            cnt.put(b, cnt.get(b) - 1);
        }
        return true;
    }
}

