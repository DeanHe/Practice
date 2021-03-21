package bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example
Example 1:

Input:org = [1,2,3], seqs = [[1,2],[1,3]]
Output: false
Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input: org = [1,2,3], seqs = [[1,2]]
Output: false
Explanation:
The reconstructed sequence can only be [1,2].
Example 3:

Input: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
Output: true
Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input:org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
Output:true*/
public class SequenceReconstruction {
	/**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // write your code here
    	Map<Integer, Set<Integer>> graph = new HashMap<>();
    	Map<Integer, Integer> indegree = new HashMap<>();
    	if(seqs == null || seqs.length == 0){
    		return false;
    	}
    	if(org.length == 0 && seqs[0].length == 0){
    	    return true;
    	}
    	if(seqs[0].length == 0){
    	    return false;
    	}
    	int N = org.length;
    	for(int num : org){
    		graph.put(num, new HashSet<>());
    		indegree.put(num, 0);
    	}
    	for(int[] seq : seqs){
    	    //single element case
    	    if(seq[0] <= 0 || seq[0] > N){
    	        return false;
    	    }
    		for(int i = 1; i < seq.length; i++){
    			int start = seq[i - 1];
    			int end = seq[i];
    			if(start <= 0 || start > N || end <= 0 || end > N){
    				return false;
    			}
    			if(graph.get(start).add(end)){
    			    indegree.put(end, indegree.get(end) + 1);
    			}
    		}
    	}
    	Queue<Integer> queue = new LinkedList<>();
    	for(int i : indegree.keySet()){
    		if(indegree.get(i) == 0){
    			queue.offer(i);
    		}
    	}
    	int index = 0;
    	while(queue.size() == 1){
    		int cur = queue.poll();
    		if(cur != org[index]){
    			return false;
    		}
    		for(int nb : graph.get(cur)){
				indegree.put(nb, indegree.get(nb) - 1);
				if(indegree.get(nb) == 0){
					queue.offer(nb);
				}		
    		}
    		index++;
    	}
    	return index == org.length;
    }
}
