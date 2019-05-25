package String;

import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.StartTlsRequest;

/*A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example
Example 1:
	Input:  S = "ababcbacadefegdehijhklij"
	Output:  [9,7,8]
	
	Explanation:
	The partitions are "ababcbaca", "defegde", "hijhklij".
	A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
	
Example 2:
	Input: S = "abcab"
	Output:  [5]
	
	Explanation:
	We can not split it. 
	
Notice
1.S will have length in range [1, 500].
2.S will consist of lowercase letters ('a' to 'z') only.

find the last index of each letter in S first.
iterate letter from start to last[start], and expand the partition to last[cur]
*/
public class PartitionLabels {
	/**
     * @param S: a string
     * @return: a list of integers representing the size of these parts
     */
    public List<Integer> partitionLabels(String S) {
    	List<Integer> res = new ArrayList<>();
    	int len = S.length();
    	int[] last = new int[26];
    	for(int i = 0; i < len; i++){
    		last[S.charAt(i) - 'a'] = i;
    	}
    	int start = 0, end = 0;
    	for(int i = 0; i < len; i++){
			end = Math.max(end, last[S.charAt(i) - 'a']);
			if(i == end){
				res.add(end - start + 1);
				start = i + 1;
			}
		}
    	return res;
    }
}
