package UnionFind;
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
public class SimilarStringGroups {
	int[] parent;
    int count;
    public int numSimilarGroupsUN(String[] A) {
        int len = A.length;
        parent = new int[len];
        for(int i = 0; i < len; i++){
            parent[i] = i;
        }
        count = len;
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                if(getRoot(i) != getRoot(j) && areSimilar(A[i], A[j])){
                    union(i, j);
                }
            }
        }
        return count;
    }
    
    private int getRoot(int x){
        int root = x;
        // find root first
        while(parent[root] != root){
            root = parent[root];
        }
        // compress path
        while(parent[x] != root){
            int father = parent[x];
            parent[x] = root;
            x = father;
        }
        return root;
    }
    private void union(int x, int y){
        int root_x = getRoot(x);
        int root_y = getRoot(y);
        if(root_x == root_y){
            return;
        }
        parent[root_x] = root_y;
        count--;
    }
    
    private boolean areSimilar(String s1, String s2){
        int diff = 0, first = -1, second = -1;
        if(s1.length() != s2.length()){
            return false;
        }
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                diff++;
                if(diff == 1){
                	first = i;
                }
                if(diff == 2){
                	second = i;
                	if(s1.charAt(first) != s2.charAt(second) || s1.charAt(second) != s2.charAt(first)){
                        return false;
                    }
                }
                if(diff > 2){
                    return false;
                }
            }
        }
        return true;
    }
}
