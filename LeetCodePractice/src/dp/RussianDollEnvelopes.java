package dp;

import java.util.Arrays;

/*You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3 
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

analysis:
sort the array. Ascend on width and ascend on height if width are same (because the requirement is strictly greater than).
Find the longest increasing subsequence (LIS) based on height.
Since the width is increasing, we only need to consider height.

TC O(NlogN)
*/
public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length != 2) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return b[1] - a[1];
            }
        });
        int len = envelopes.length, res = 0;
        // dp[i] means longest increasing sequence ended with envelopes[i] of its height
        int[] dp = new int[len];
        for (int[] envelope : envelopes) {
            int idx = binarySearch(dp, 0, res - 1, envelope[1]);
            dp[idx] = envelope[1];
            if (idx == res) {
                res++;
            }
        }
        return res;
    }

    private int binarySearch(int[] arr, int s, int e, int target) {
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return s;
    }
}
