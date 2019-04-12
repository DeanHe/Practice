package Trie;

import java.util.*;

/*Given a list of words, we may encode it by writing a reference string S and a list of indexes A.

For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].

Then for each index, we will recover the word by reading from the reference string from that index until we reach a "#" character.

What is the length of the shortest reference string S possible that encodes the given words?

Example:

Input: words = ["time", "me", "bell"]
Output: 10
Explanation: S = "time#bell#" and indexes = [0, 2, 5].*/

public class ShortEncodingOfWords {
	class TrieNode {
		TrieNode[] children;
		int childrenCount;
		TrieNode() {
			children = new TrieNode[26];
			childrenCount = 0;
		}
		TrieNode get(char c){
			if(children[c- 'a'] == null){
				children[c- 'a'] = new TrieNode();
				childrenCount++;
			}
			return children[c- 'a'];
		}
	}
	//To find whether different words have the same suffix, let's put them backwards into a trie (prefix tree). 
	//For example, if we have "time" and "me", we will put "emit" and "em" into our trie.
	//After, the leaves of this trie (nodes with no children) represent words that have no suffix, 
	//and we will count sum(word.length + 1 for word in words).
	public int minimumLengthEncoding(String[] words) {
        TrieNode root = new TrieNode();
        Map<TrieNode, Integer> NodeWordIndex = new HashMap<>();
        for(int i = 0; i < words.length; i++){
        	String word = words[i];
        	TrieNode cur = root;
        	for(int j = word.length() - 1; j >= 0; j--){
        		char c = word.charAt(j);
        		cur = cur.get(c);
        	}
        	NodeWordIndex.put(cur, i);
        }
        int ans = 0;
        for(TrieNode cur : NodeWordIndex.keySet()){
        	if(cur.childrenCount == 0){
        		String word = words[NodeWordIndex.get(cur)];
        		ans += word.length() + 1;
        	}
        }
        return ans;
    }
}
