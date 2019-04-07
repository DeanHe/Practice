package BFS;
/*Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?

Example 1:

Input: ["tars","rats","arts","star"]
Output: 2
Note:

A.length <= 2000
A[i].length <= 1000
A.length * A[i].length <= 20000
All words in A consist of lowercase letters only.
All words in A have the same length and are anagrams of each other.
The judging time limit has been increased for this question.*/

import java.util.*;

public class SimilarStringGroups {
    public int numSimilarGroups(String[] A) {
        Set<String> unchecked = new HashSet<>();
        for(String s : A){
            unchecked.add(s);
        }
        int group = 0;
        for(String s : A){
            if(unchecked.contains(s)){
                group++;
                Queue<String> queue = new LinkedList<>();
                queue.offer(s);
                while(!queue.isEmpty()){
                    String cur = queue.poll();
                    List<String> neighbors = new ArrayList<>();
                    for(String candidate_nb : unchecked){
                        if(areSimilar(cur, candidate_nb)){
                            neighbors.add(candidate_nb);
                        }
                    }
                    for(String nb : neighbors){
                        queue.offer(nb);
                        unchecked.remove(nb);
                    }
                }
            }
        }
        return group;
    }
    
    private boolean areSimilar(String s1, String s2){
        int diff = 0;
        if(s1.length() != s2.length()){
            return false;
        }
        char c1 = 'a', c2 = 'a';
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
            	diff++;
            	if(diff == 1){
            		c1 = s1.charAt(i);
            		c2 = s1.charAt(i);
            	} else if(diff == 2){
            		if(s1.charAt(i) != c2 || s2.charAt(i) != c1){
            			return false;
            		}
                } else if(diff > 2){
                	return false;
                }
            }
        }
        return true;
    }
}
