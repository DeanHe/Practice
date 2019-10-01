package DP;

import java.util.Arrays;

/* arrange events to have max interest,
 * the event is this format: [start, end, interestLevel] 
 */
public class MaxInterestEvent {
	public int arrangeEvent(int[][] events) {
		int len = events.length;
		int[] dp = new int[len];
		int res = 0;
		Arrays.sort(events, (int[] a, int[] b) -> {
			if(a[1] != b[1]){
				return a[1] - b[1];
			}
			return a[0] - b[0];
		});
		for(int i = 0; i < len; i++){
			int start = events[i][0];
			int end = events[i][1];
			int interest = events[i][2];
			dp[i] = interest;
			int preMax = 0;
			for(int j = i - 1; j >= 0; j--){
				int preStart = events[j][0];
				int preEnd = events[j][1];
				if(preEnd < start){
					break;
				}
				if(preStart < start && start <= preEnd && end > preEnd){
					preMax = Math.max(preMax, dp[j]);
				}
			}
			dp[i] += preMax;
			res = Math.max(res, dp[i]);
		}
		return res;
	}
}
