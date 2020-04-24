package DP;

import java.util.Arrays;
import java.util.Comparator;

/*You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3 
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

Sort the array. Ascend on width and ascend on height if width are same.
Find the longest increasing subsequence based on height.
Since the width is increasing, we only need to consider height.
*/
public class RussianDollEnvelopes {
	public int maxEnvelopes(int[][] envelopes) {
		if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length != 2) {
			return 0;
		}
		Arrays.sort(envelopes, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] != b[0]) {
					return a[0] - b[0];
				}
				return a[1] - b[1];
			}
		});
		int len = envelopes.length;
		// mem[i] means longest increasing sequence ended with envelopes[i]
		int[] dp = new int[len];
		int maxOverlap = 1;
		for (int i = 0; i < len; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxOverlap = Math.max(maxOverlap, dp[i]);
		}
		return maxOverlap;
	}
}
