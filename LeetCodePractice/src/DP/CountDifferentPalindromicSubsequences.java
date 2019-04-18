package DP;

import java.util.TreeSet;

/*Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.

A subsequence of a string S is obtained by deleting 0 or more characters from S.

A sequence is palindromic if it is equal to the sequence reversed.

Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.

Example 1:
Input: 
S = 'bccb'
Output: 6
Explanation: 
The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
Note that 'bcb' is counted only once, even though it occurs twice.
Example 2:
Input: 
S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
Output: 104860361
Explanation: 
There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
Note:

The length of S will be in the range [1, 1000].
Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.

http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-730-count-different-palindromic-subsequences/
*/
public class CountDifferentPalindromicSubsequences {
	int mod = 1000000007;
	public int countPalindromicSubsequences(String s) {
		int len = s.length();
        int[][] dp = new int[len][len];

        char[] arr = s.toCharArray();
        for(int i = 0; i < len; i++){
            dp[i][i] = 1;   // Consider the test case "a", "b" "c"...
        }
        for(int dist = 1; dist < len; dist++){
            for(int i = 0; i + dist < len; i++){
                int j = i + dist;
                if(arr[i] == arr[j]){
                	int left = i + 1;
                	int right = j - 1;
                	/* Variable left and right here are used to get rid of the duplicate 
                	 * left: first pos of arr[i] in arr[i + 1 : j - 1]
            		 * right: last pos of arr[i] in arr[i + 1 : j - 1] */
                	while(left <= right && arr[left] != arr[i]){
                		left++;
                	}
                	while(left <= right && arr[right] != arr[i]){
                		right--;
                	}
                	if(left > right){
                		/* arr[i] is not in arr[i + 1 : j - 1] */
                		dp[i][j] = 2 * dp[i + 1][j - 1] + 2;
                	} else if (left == right){
                		/* arr[i] appear only once in arr[i + 1 : j - 1] */
                		dp[i][j] = 2 * dp[i + 1][j - 1] + 1;
                	} else {
                		dp[i][j] = 2 * dp[i + 1][j - 1] - dp[left + 1][right - 1];
                	}
                } else {
                	dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % mod;
            }
        }
        return dp[0][len - 1];
	}
	//
	public int countPalindromicSubsequencesTopDown(String s) {
		int len = s.length();
		TreeSet<Integer>[] characters = new TreeSet[26];
		for(int i = 0; i < 26; i++){
			characters[i] = new TreeSet<Integer>();
		}
		for(int i = 0; i < len; i++){
			int idx = s.charAt(i) - 'a';
			characters[idx].add(i);
		}
        Integer[][] dp = new Integer[len + 1][len + 1];
        return dfs(characters, dp, 0, len);                                                          
    }
	private int dfs(TreeSet<Integer>[] characters, Integer[][] dp, int start, int end){
		if(start >= end){
			return 0;
		}
		if(dp[start][end] != null){
			return dp[start][end];
		}
		long ans = 0;
		for(int i = 0; i < 26; i++){
			Integer new_start = characters[i].ceiling(start);
			Integer new_end = characters[i].lower(end);
			if(new_start == null || new_start >= end){
				continue;
			}
			ans++;
			if(new_start != new_end){
				ans++;
			}
			ans += dfs(characters, dp, new_start + 1, new_end);
		}
		
		dp[start][end] = (int)(ans % mod);
		return dp[start][end];
	}
}
