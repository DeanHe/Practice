package dp.bitmask;

import java.util.Arrays;

/*
Given an array of strings words, return the smallest string that contains each string in words as a substring.
If there are multiple valid strings of the smallest length, return any of them.

You may assume that no string in words is a substring of another string in words.
 
Example 1:

Input: ["alex","loves","leetcode"]
Output: "alexlovesleetcode"
Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
Example 2:

Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
Output: "gctaagttcatgcatc"
 

Note:

1 <= A.length <= 12
1 <= A[i].length <= 20
words[i] consists of lowercase English letters.
All the strings of words are unique.
*/
public class FindTheShortestSuperstring {
	public String shortestSuperstring(String[] words) {
        int N = words.length;
        // preprocess append
        int[][] append = new int[N][N];
        for(int i = 0; i < N; i++){
        	for(int j = 0; j < N; j++){
        		append[i][j] = getAppendLength(words[i], words[j]);
        	}
        }
        //mem[s][i] means minimum length of state s, ends with string words[i]
        int[][] dp = new int[1 << N][N];
        for(int[] rows : dp){
        	Arrays.fill(rows, Integer.MAX_VALUE);
        }
        //backNode[s][i] means string k leads to mem[s][i]
        int[][] backNode = new int[1 << N][N];
        for(int[] rows : backNode){
        	Arrays.fill(rows, -1);
        }
        // the last substring of superString from words, minLen of superString
        int last = -1, minLen = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
        	dp[1 << i][i] = words[i].length();
        }
        for(int s = 1; s < (1 << N); s++){
        	for(int i = 0; i < N; i++){
        		if((s & (1 << i)) > 0){
        			int pre_s = s - (1 << i);
        			for(int j = 0; j < N; j++){
        				if(dp[pre_s][j] != Integer.MAX_VALUE && dp[pre_s][j] + append[j][i] < dp[s][i]){
        					dp[s][i] = dp[pre_s][j] + append[j][i];
        					backNode[s][i] = j;
        				}
        			}
        		}
        		if(s == (1 << N) - 1 && minLen > dp[s][i]){
        			minLen = dp[s][i];
        			last = i;
        		}
        	}
        }
        StringBuilder sb = new StringBuilder();
        int s = (1 << N) - 1;
        int cur = last;
        while(s > 0){
        	int pre = backNode[s][cur];
        	if(pre == -1){
        		sb.insert(0, words[cur]);
        	} else {
        		String appendPart = words[cur].substring(words[cur].length() - append[pre][cur]);
        		sb.insert(0, appendPart);
        	}
        	s = s - (1 << cur);
            cur = pre;
        }
        return sb.toString();
    }
	private int getAppendLength(String a, String b){
		int aLen = a.length();
		int bLen = b.length();
		int overlap = 0;
		for(int i = 1; i <= Math.min(aLen, bLen); i++){
            String bPrefix = b.substring(0, i);
            String aSuffix = a.substring(aLen - i);
			if(bPrefix.equals(aSuffix)){
				overlap = i;
			}
		}
		return b.length() - overlap;
	}
}
