package trie;

import java.util.HashSet;
import java.util.Set;

/*
Given a list of strings, for each string, output the shortest substring that only appears in that string

example:
Input: [ "palantir", "pelantors","cheapair", "cheapoair"]
output:{
	"palantir": "ti", # ti only appears in "palantir"
	"pelantors": "s", # s only appears in "pelantors"
	"cheapair": "pai" or "apa", # either substring only appears in "cheapair"
	"cheapoair": "po" or "oa" # either substring only appears in cheapoair
}
 */
public class ShortestUniqueSubstring {
    public String[] uniqueSubstrings(String[] input) {
        TrieNode root = new TrieNode();
        int len = input.length;
        String[] res = new String[len];
        for (int i = 0; i < len; i++) {
            String s = input[i];
            for (int j = 0; j < s.length(); j++) {
                for (int k = j + 1; k <= s.length(); k++) {
                    String sub = s.substring(j, k);
                    addToTrie(root, sub, i);
                }
            }
        }
        dfs(root, res, new StringBuilder());
        return res;
    }

    private void dfs(TrieNode root, String[] res, StringBuilder sb) {
        if (root != null) {
            if (root.set.size() == 1) {
                int idx = root.set.iterator().next();
                if (res[idx] == null || sb.length() < res[idx].length()) {
                    res[idx] = sb.toString();
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            TrieNode child = root.children[i];
            if (child != null) {
                sb.append((char) ('a' + i));
                dfs(child, res, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    private void addToTrie(TrieNode root, String s, int idx) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.set.add(idx);
    }

    private class TrieNode {
        public TrieNode[] children;
        public Set<Integer> set;

        public TrieNode() {
            children = new TrieNode[26];
            set = new HashSet<>();
        }
    }
}
