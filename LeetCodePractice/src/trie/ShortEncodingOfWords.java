package trie;

import java.util.HashMap;
import java.util.Map;

/*
#820

Given a list of words, we may encode it by writing a reference string S and a list of indexes A.

For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].

Then for each index, we will recover the word by reading from the reference string from that index until we reach a "#" character.

What is the length of the shortest reference string S possible that encodes the given words?

Example:

Input: words = ["time", "me", "bell"]
Output: 10
Explanation: S = "time#bell#" and indexes = [0, 2, 5].

analysis:
Insert all reversed words to the trie.
maintain the childCount to know which node is a leaf
sum up all leaf's correspondent word's length

TC: O(multiply of len(word))
*/

public class ShortEncodingOfWords {
    private class TrieNode {
        TrieNode[] children;
        boolean hasChild;

        public TrieNode() {
            children = new TrieNode[26];
            hasChild = false;
        }

        public TrieNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
                hasChild = true;
            }
            return children[c - 'a'];
        }
    }

    public int minimumLengthEncoding(String[] words) {
        TrieNode root = new TrieNode();
        Map<TrieNode, Integer> lenMap = new HashMap<>();
        for (String word : words) {
            TrieNode cur = root;
            for (int j = word.length() - 1; j >= 0; j--) {
                char c = word.charAt(j);
                cur = cur.get(c);
            }
            lenMap.put(cur, word.length());
        }
        int res = 0;
        for (TrieNode cur : lenMap.keySet()) {
            if (!cur.hasChild) {
                res += lenMap.get(cur) + 1;
            }
        }
        return res;
    }
}
