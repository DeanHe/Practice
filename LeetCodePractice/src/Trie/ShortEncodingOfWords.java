package Trie;

import java.util.*;

//For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].
public class ShortEncodingOfWords {
	class TrieNode {
		TrieNode[] children;
		int count;
		TrieNode() {
			children = new TrieNode[26];
			count = 0;
		}
		TrieNode get(char c){
			if(children[c- 'a'] == null){
				children[c- 'a'] = new TrieNode();
				count++;
			}
			return children[c- 'a'];
		}
	}
	//To find whether different words have the same suffix, let's put them backwards into a trie (prefix tree). 
	//For example, if we have "time" and "me", we will put "emit" and "em" into our trie.
	//After, the leaves of this trie (nodes with no children) represent words that have no suffix, 
	//and we will count sum(word.length + 1 for word in words).
	public int minimumLengthEncoding(String[] words) {
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> NodeWordIndex = new HashMap<>();
        for(int i = 0; i < words.length; i++){
        	String word = words[i];
        	TrieNode cur = trie;
        	for(int j = word.length() - 1; j >= 0; j--){
        		char c = word.charAt(j);
        		cur = cur.get(c);
        	}
        	NodeWordIndex.put(cur, i);
        }
        int ans = 0;
        for(TrieNode cur : NodeWordIndex.keySet()){
        	if(cur.count == 0){
        		String word = words[NodeWordIndex.get(cur)];
        		ans += word.length() + 1;
        	}
        }
        return ans;
    }
}
