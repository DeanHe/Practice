package contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.

You are also given a string s of length n, where s[i] is the character assigned to node i.

Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.

Example 1:
Input: parent = [-1,0,0,1,1,2], s = "abacbe"
Output: 3
Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
It can be proven that there is no longer path that satisfies the conditions.

Example 2:
Input: parent = [-1,0,0,0], s = "aabc"
Output: 3
Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.


Constraints:
n == parent.length == s.length
1 <= n <= 10^5
0 <= parent[i] <= n - 1 for all i >= 1
parent[0] == -1
parent represents a valid tree.
s consists of only lowercase English letters.
 */
public class LongestPathWithDifferentAdjacentCharacters {
    class Node {
        char c;
        List<Node> children;

        public Node(char c){
            this.c = c;
            children = new ArrayList<>();
        }
    }

    class ResultType {
        //singleLen from this node, maxLen below this node.
        public int maxLen, singleLen;
        public ResultType(int maxLen, int singleLen){
            this.maxLen = maxLen;
            this.singleLen = singleLen;
        }
    }

    public int longestPath(int[] parent, String s) {
        int len = parent.length;
        Map<Integer, Node> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            Node cur = new Node(s.charAt(i));
            map.put(i, cur);
        }
        for(int i = 0; i < len; i++){
            if(parent[i] != -1){
                map.get(parent[i]).children.add(map.get(i));
            }
        }
        Node root = map.get(0);
        return dfs(root).maxLen;
    }

    private ResultType dfs(Node root) {
        int maxLen = 1, first = 0, second = 0;
        for(Node child : root.children){
            ResultType tmp = dfs(child);
            if(child.c != root.c){
                if(first < tmp.singleLen){
                    second = first;
                    first = tmp.singleLen;
                } else if(second < tmp.singleLen){
                    second = tmp.singleLen;
                }
            }
            maxLen = Math.max(maxLen, tmp.maxLen);
        }
        maxLen = Math.max(maxLen, 1 + first + second);
        return new ResultType(maxLen, 1 + first);
    }
}
