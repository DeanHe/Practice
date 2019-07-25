package Greedy;

import java.util.*;

/*A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.*/
public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        char[] arr = S.toCharArray();
        int len = arr.length;
        int[] lastIdx = new int[26];
        for(int i = 0; i < len; i++){
        	int c = arr[i] - 'a';
        	lastIdx[c] = i;
        }
        int start = 0, end = 0;
        for(int i = 0; i < len; i++){
        	int c = arr[i] - 'a';
        	end = Math.max(end, lastIdx[c]);
        	if(i == end){
        		res.add(end - start + 1);
        		start = end + 1;
        	}
        }
        return res;
    }
}
