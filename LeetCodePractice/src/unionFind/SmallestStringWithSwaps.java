package unionFind;

import java.util.*;

/*
You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.

Example 1:
Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"

Example 2:
Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
Output: "abcd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[0] and s[2], s = "acbd"
Swap s[1] and s[2], s = "abcd"

Example 3:
Input: s = "cba", pairs = [[0,1],[1,2]]
Output: "abc"
Explaination: 
Swap s[0] and s[1], s = "bca"
Swap s[1] and s[2], s = "bac"
Swap s[0] and s[1], s = "abc"

Constraints:
1 <= s.length <= 10^5
0 <= pairs.length <= 10^5
0 <= pairs[i][0], pairs[i][1] < s.length
s only contains lower case English letters.
*/
public class SmallestStringWithSwaps {
    private int[] parent, size;

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int len = s.length();
        parent = new int[len];
        size = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        for (List<Integer> swap : pairs) {
            union(swap.get(0), swap.get(1));
        }
        Map<Integer, List<Character>> graph = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int root = findRoot(i);
            List<Character> ls = graph.computeIfAbsent(root, (a) -> new ArrayList<>());
            ls.add(s.charAt(i));
        }
        for(List<Character> ls : graph.values()){
            Collections.sort(ls);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++){
            int root = findRoot(i);
            List<Character> ls = graph.get(root);
            char c = ls.remove(0);
            sb.append(c);
        }
        return sb.toString();
    }

    private int findRoot(int x) {
        int root = x;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (x != root) {
            int fa = parent[x];
            parent[x] = root;
            x = fa;
        }
        return root;
    }

    private void union(int a, int b) {
        int root_a = findRoot(a);
        int root_b = findRoot(b);
        if (root_a != root_b) {
            if (size[root_a] < size[root_b]) {
                parent[root_a] = root_b;
                size[root_b] += size[root_a];
                size[root_a] = 0;
            } else {
                parent[root_b] = root_a;
                size[root_a] += size[root_b];
                size[root_b] = 0;
            }
        }
    }
}
