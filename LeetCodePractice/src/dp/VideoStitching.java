package dp;

import java.util.Arrays;

/*
You are given a series of video clips from a sporting event that lasted T seconds.  These video clips can be overlapping with each other and have varied lengths.

Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].  We can cut these clips into segments freely: for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].

Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event ([0, T]).  If the task is impossible, return -1.


Example 1:
Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
Output: 3
Explanation: 
We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
Then, we can reconstruct the sporting event as follows:
We cut [1,9] into segments [1,2] + [2,8] + [8,9].
Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].

Example 2:
Input: clips = [[0,1],[1,2]], T = 5
Output: -1
Explanation: 
We can't cover [0,5] with only [0,1] and [0,2].

Example 3:
Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
Output: 3
Explanation: 
We can take clips [0,4], [4,7], and [6,9].

Example 4:
Input: clips = [[0,4],[2,8]], T = 5
Output: 2
Explanation: 
Notice you can have extra video after the event ends.
 

Note:
1 <= clips.length <= 100
0 <= clips[i][0], clips[i][1] <= 100
0 <= T <= 100

analysis:
approach 1
dp[i][j] means minimum number of clips needed to cover range [i:j]
TC O(N ^ 2)

approach 2
greedy
TC O(N log N)
*/
public class VideoStitching {
	public int videoStitching(int[][] clips, int T) {
		int UPPER_BOUND = 101;
        int[][] dp = new int[T + 1][T + 1];
        for(int i = 0; i <= T; i++){
            for(int j = 0; j <= T; j++){
                dp[i][j] = UPPER_BOUND;
            }
        }
        for(int[] clip : clips ){
        	int c_start = clip[0];
        	int c_end = clip[1];
        	for(int l = 1; l <= T; l++){
        		for(int i = 0; i + l <= T; i++){
        			int j = i + l;
        			if(c_end < i || c_start > j){
        				continue;
        			} else if(c_start <= i && j <= c_end){
        				dp[i][j] = 1;
        			}else if(j <= c_end){
        				dp[i][j] = Math.min(dp[i][j], dp[i][c_start] + 1);
        			} else if(c_start <= i){
        				dp[i][j] = Math.min(dp[i][j], dp[c_end][j] + 1);
        			} else {
        				dp[i][j] = Math.min(dp[i][j], dp[i][c_start] + 1 + dp[c_end][j]);
        			}
        		}
        	}
        }
        return dp[0][T]  == UPPER_BOUND ? -1 : dp[0][T];
    }
	public int videoStitchingByGreedy(int[][] clips, int T) {
		int len = clips.length;
		Arrays.sort(clips, (a, b) -> a[0] - b[0]);
		int i = 0, count = 0;
		int end = 0;
		while(i < len){
			if(end < clips[i][0]){
				return -1;
			}
			int maxEnd = end;
			while(i < len && clips[i][0] <= end){
				maxEnd = Math.max(maxEnd, clips[i][1]);
				i++;
			}
			count++;
			end = maxEnd;
			if(end >= T){
				return count;
			}
		}
		return -1;
	}
}
