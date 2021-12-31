package trie;
/**
 * Your trie object will be instantiated and called as such:
 * trie trie = new trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
public class ImplementTrie {
	private TrieNode root;

    public ImplementTrie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p = root;
        char[] arr = word.toCharArray();
        for(char c : arr){
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
        if(p != null && p.isEnd){
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
        for(char c : word.toCharArray()){
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
