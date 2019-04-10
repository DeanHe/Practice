package Trie;
/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
public class ImplementTrie {
	class TrieNode {
	    TrieNode[] arr;
	    boolean isEnd;
	    // Initialize your data structure here.
	    public TrieNode() {
	        arr = new TrieNode[26];
	        isEnd = false;
	    }
	}
	private TrieNode root;

    public ImplementTrie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p = root;
        int len = word.length();
        for(int i = 0; i < len; i++){
            char c = word.charAt(i);
            int index = c - 'a';
            if(p.arr[index] == null){
                p.arr[index] = new TrieNode();
            }
            p = p.arr[index];
        }
        p.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = searchNode(word);
        if(p != null && p.isEnd == true){
            return true;
        } else {
            return false;
        }
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = searchNode(prefix);
        if(p != null){
            return true;
        } else {
            return false;
        }
    }
    
    private TrieNode searchNode(String word) {
        TrieNode p = root;
        int len = word.length();
        for(int i = 0; i < len; i++){
            char c = word.charAt(i);
            int index = c - 'a';
            if(p.arr[index] != null){
                p = p.arr[index];
            } else {
                return null;
            }
        }
        if(p == root){
            return null;
        }
        return p;
    }
}
