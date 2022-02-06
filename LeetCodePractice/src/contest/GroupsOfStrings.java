package contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
You are given a 0-indexed array of strings words. Each string consists of lowercase English letters only. No letter occurs more than once in any string of words.

Two strings s1 and s2 are said to be connected if the set of letters of s2 can be obtained from the set of letters of s1 by any one of the following operations:

Adding exactly one letter to the set of the letters of s1.
Deleting exactly one letter from the set of the letters of s1.
Replacing exactly one letter from the set of the letters of s1 with any letter, including itself.
The array words can be divided into one or more non-intersecting groups. A string belongs to a group if any one of the following is true:

It is connected to at least one other string of the group.
It is the only string present in the group.
Note that the strings in words should be grouped in such a manner that a string belonging to a group cannot be connected to a string present in any other group. It can be proved that such an arrangement is always unique.

Return an array ans of size 2 where:

ans[0] is the total number of groups words can be divided into, and
ans[1] is the size of the largest group.


Example 1:

Input: words = ["a","b","ab","cde"]
Output: [2,3]
Explanation:
- words[0] can be used to obtain words[1] (by replacing 'a' with 'b'), and words[2] (by adding 'b'). So words[0] is connected to words[1] and words[2].
- words[1] can be used to obtain words[0] (by replacing 'b' with 'a'), and words[2] (by adding 'a'). So words[1] is connected to words[0] and words[2].
- words[2] can be used to obtain words[0] (by deleting 'b'), and words[1] (by deleting 'a'). So words[2] is connected to words[0] and words[1].
- words[3] is not connected to any string in words.
Thus, words can be divided into 2 groups ["a","b","ab"] and ["cde"]. The size of the largest group is 3.
Example 2:

Input: words = ["a","ab","abc"]
Output: [1,3]
Explanation:
- words[0] is connected to words[1].
- words[1] is connected to words[0] and words[2].
- words[2] is connected to words[1].
Since all strings are connected to each other, they should be grouped together.
Thus, the size of the largest group is 3.


Constraints:

1 <= words.length <= 2 * 10^4
1 <= words[i].length <= 26
words[i] consists of lowercase English letters only.
No letter occurs more than once in words[i].

hint:
1 Can we build a graph from words, where there exists an edge between nodes i and j if words[i] and words[j] are connected?
2 The problem now boils down to finding the total number of components and the size of the largest component in the graph.
3 How can we use bit masking to reduce the search space while adding edges to node i?

analysis:
union find, bit mask to represent the string variant
 */
public class GroupsOfStrings {
    int[] parent, size;

    public int[] groupStrings(String[] words) {
        int len = words.length;
        parent = new int[len];
        size = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        int[] mask = new int[len];
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            mask[i] = hash(words[i]);
            idxMap.put(mask[i], i);
        }
        for (int i = 0; i < len; i++) {
            String w = words[i];
            int state = mask[i];
            for (char c : w.toCharArray()) {
                //delete one char
                state = state & (~(1 << (c - 'a')));
                if (idxMap.containsKey(state)) {
                    int j = idxMap.get(state);
                    union(i, j);
                }
                // replace one char, replace = delete(already done) + add
                for(char t = 'a'; t <= 'z'; t++){
                    int d = (state >> (t - 'a')) & 1;
                    if(d == 0){ // this letter not occurred yet
                        state = state | (1 << (t - 'a'));
                        if(idxMap.containsKey(state)){
                            int j = idxMap.get(state);
                            union(i, j);
                        }
                        //backtracking replace
                        state = state & (~(1 << (t - 'a')));
                    }
                }
                //backtracking delete
                state = state | (1 << (c - 'a'));
            }
        }
        Set<Integer> set = new HashSet<>();
        int maxGroupSize = 0;
        for(int i = 0; i < len; i++){
            int root = findRoot(i);
            set.add(root);
            maxGroupSize = Math.max(maxGroupSize, size[root]);
        }
        return new int[]{set.size(), maxGroupSize};
    }

    private int hash(String word) {
        int res = 0;
        for(char c : word.toCharArray()){
            res = res | (1 << (c - 'a'));
        }
        return res;
    }

    private int findRoot(int x){
        int root = x;
        while(root != parent[root]){
            root = parent[root];
        }
        while(x != root){
            int fa = parent[x];
            parent[x] = root;
            x = fa;
        }
        return root;
    }

    private boolean union(int a, int b){
        int root_a = findRoot(a);
        int root_b = findRoot(b);
        if(root_a == root_b){
            return false;
        }
        parent[root_a] = root_b;
        size[root_b] += size[root_a];
        size[root_a] = 0;
        return true;
    }
}
