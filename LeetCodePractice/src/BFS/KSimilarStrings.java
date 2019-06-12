package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.

Given two anagrams A and B, return the smallest K for which A and B are K-similar.

Example 1:

Input: A = "ab", B = "ba"
Output: 1
Example 2:

Input: A = "abc", B = "bca"
Output: 2
Example 3:

Input: A = "abac", B = "baca"
Output: 2
Example 4:

Input: A = "aabc", B = "abca"
Output: 2
Note:

1 <= A.length == B.length <= 20
A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
*/
public class KSimilarStrings {
	public int kSimilarity(String A, String B) {
        int len = A.length();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(A);
        queue.offer(A);
        int step = 0;
        while(!queue.isEmpty()){
        	int size = queue.size();
        	for(int i = 0; i < size; i++){
        		String cur = queue.poll();
            	if(cur.equals(B)){
            		return step;
            	}
            	int j = 0;
            	while(j < len && cur.charAt(j) == B.charAt(j)){
            		j++;
            	}
            	for(int k = j + 1; k < len; k++){
            		if(cur.charAt(k) == B.charAt(j) && cur.charAt(k) != B.charAt(k)){
            			String nb = swap(cur, k, j);
            			if(!visited.contains(nb)){
            				visited.add(nb);
            				queue.offer(nb);
            			}
            		}
            	}
        	}
        	step++;
        }
        return step;
    }
	private String swap(String S, int i, int j){
		char[] arr = S.toCharArray();
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return new String(arr);
	}
}
