package trie;
/*
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2


Constraints:

1 <= s.length <= 5 * 104
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.

analysis:

 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class NumberOfMatchingSubsequencesGoogle {
    public List<String> numMatchingSubseq(String s, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(root, word);
        }
        List<TrieNode> candidates = new ArrayList<>();
        candidates.add(root);
        for (char c : s.toCharArray()) {
            int sz = candidates.size();
            for (int i = 0; i < sz; i++) {
                TrieNode cur = candidates.get(i);
                search(cur, c, candidates, res);
            }
        }
        return res.stream().distinct().collect(Collectors.toList());
    }

    private void insert(TrieNode root, String word) {
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (root.children[idx] == null) {
                root.children[idx] = new TrieNode();
            }
            root = root.children[idx];
        }
        root.end = word;
    }

    private void search(TrieNode root, char c, List<TrieNode> candidates, List<String> res) {
        int idx = c - 'a';
        if (root.children[idx] == null) {
            return;
        }
        root = root.children[idx];
        candidates.add(root);
        if (root.end.length() > 0) {
            res.add(root.end);
        }
    }

    private class TrieNode {
        TrieNode[] children;
        String end;

        public TrieNode() {
            children = new TrieNode[26];
            end = "";
        }
    }
}
