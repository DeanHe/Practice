package segmentTree;
/*
You are given a 0-indexed string s. You are also given a 0-indexed string queryCharacters of length k and a 0-indexed array of integer indices queryIndices of length k, both of which are used to describe k queries.

The ith query updates the character in s at index queryIndices[i] to the character queryCharacters[i].

Return an array lengths of length k where lengths[i] is the length of the longest substring of s consisting of only one repeating character after the ith query is performed.


Example 1:
Input: s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
Output: [3,3,4]
Explanation:
- 1st query updates s = "bbbacc". The longest substring consisting of one repeating character is "bbb" with length 3.
- 2nd query updates s = "bbbccc".
  The longest substring consisting of one repeating character can be "bbb" or "ccc" with length 3.
- 3rd query updates s = "bbbbcc". The longest substring consisting of one repeating character is "bbbb" with length 4.
Thus, we return [3,3,4].

Example 2:
Input: s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
Output: [2,3]
Explanation:
- 1st query updates s = "abazz". The longest substring consisting of one repeating character is "zz" with length 2.
- 2nd query updates s = "aaazz". The longest substring consisting of one repeating character is "aaa" with length 3.
Thus, we return [2,3].


Constraints:
1 <= s.length <= 10^5
s consists of lowercase English letters.
k == queryCharacters.length == queryIndices.length
1 <= k <= 10^5
queryCharacters consists of lowercase English letters.
0 <= queryIndices[i] < s.length

hint:
1 Use a segment tree to perform fast point updates and range queries.
2 We need each segment tree node to store the length of the longest substring of that segment consisting of only 1 repeating character.
3 We will also have each segment tree node store the leftmost and rightmost character of the segment,
the max length of a prefix substring consisting of only 1 repeating character, and the max length of a suffix substring consisting of only 1 repeating character.
4 Use this information to properly merge the two segment tree nodes together.
 */
public class LongestSubstringOfOneRepeatingCharacter {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int len = queryIndices.length;
        int[] res = new int[len];
        Node root = buildSegmentTree(s);
        for(int i = 0; i < len; i++){
            int idx = queryIndices[i];
            modify(root, queryCharacters.charAt(i), idx, 0, s.length() - 1);
            res[i] = root.most;
        }
        return res;
    }

    private Node buildSegmentTree(String s) {
        Node root = dfs(s, 0, s.length() - 1);
        return root;
    }

    private Node dfs(String s, int l, int r) {
        if(l == r){
            Node root = new Node(s.charAt(l), s.charAt(r), 1, 1, 1, true);
            return root;
        }
        int mid = l + (r - l) / 2;
        Node left = dfs(s, l, mid);
        Node right = dfs(s, mid + 1, r);
        Node root = merge(left, right);
        return root;
    }

    private Node merge(Node left, Node right) {
        int most = Math.max(left.most, right.most);
        if(left.rc == right.lc){
            most = Math.max(most, left.postfixLen + right.prefixLen);
        }
        int prefixLen = left.prefixLen;
        if(left.same && left.rc == right.lc){
            prefixLen += right.prefixLen;
        }
        int postfixLen = right.postfixLen;
        if(right.same && left.rc == right.lc){
            postfixLen += left.postfixLen;
        }
        boolean same = left.same && right.same && left.rc == right.lc;
        Node root = new Node(left.lc, right.rc, most, prefixLen, postfixLen, same);
        root.left = left;
        root.right = right;
        return root;
    }

    private void modify(Node root, char c, int idx, int l, int r){
        if(l == r && idx == l){
            root.lc = c;
            root.rc = c;
            return;
        }
        int mid = l + (r - l) / 2;
        if(0 <= idx && idx <= mid){
            modify(root.left, c, idx, l, mid);
        } else {
            modify(root.right, c, idx, mid + 1, r);
        }
        root.most = Math.max(root.left.most, root.right.most);
        if(root.left.rc == root.right.lc){
            root.most = Math.max(root.most, root.left.postfixLen + root.right.prefixLen);
        }
        root.prefixLen = root.left.prefixLen;
        if(root.left.same && root.left.rc == root.right.lc){
            root.prefixLen += root.right.prefixLen;
        }
        root.postfixLen = root.right.postfixLen;
        if(root.right.same && root.left.rc == root.right.lc){
            root.postfixLen += root.left.postfixLen;
        }
        root.lc = root.left.lc;
        root.rc = root.right.rc;
        root.same = root.left.same && root.right.same && root.left.rc == root.right.lc;
    }

    class Node {
        int most, prefixLen, postfixLen;
        char lc, rc;
        Node left, right;
        boolean same;

        Node(char lc, char rc, int most, int prefixLen, int postfixLen, boolean same){
            this.lc= lc;
            this.rc = rc;
            this.most = most;
            this.prefixLen = prefixLen;
            this.postfixLen = postfixLen;
            this.same = same;
        }
    }

}
