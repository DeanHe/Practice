package Trie;

/*Design a data structure that supports the following two operations: addWord(word) and search(word)

search(word) can search a literal word or a regular expression string containing only letters a-z or ..

A . means it can represent any one letter.

Example
Example 1:

Input:
  addWord("a")
  search(".")
Output:
  true
Example 2:

Input:
  addWord("bad")
  addWord("dad")
  addWord("mad")
  search("pad")  
  search("bad")  
  search(".ad")  
  search("b..")  
Output:
  false
  true
  true
  true
Notice
You may assume that all words are consist of lowercase letters a-z.*/
public class AddandSearchWord {
	TrieNode root;

	public AddandSearchWord() {
		root = new TrieNode();
	}

	// Adds a word into the data structure.
	public void addWord(String word) {
		// Write your code here
		TrieNode p = root;
		for (char c : word.toCharArray()) {
			int index = c - 'a';
			if (p.arr[index] == null) {
				p.arr[index] = new TrieNode();
			}
			p = p.arr[index];
		}
		p.isEnd = true;
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		// Write your code here
		return dfs(root, 0, word.toCharArray());
	}

	private boolean dfs(TrieNode root, int pos, char[] word) {
		if (root.isEnd && pos == word.length) {
			return true;
		}
		if (pos >= word.length) {
			return false;
		}
		char c = word[pos];
		if (c == '.') {
			for (TrieNode child : root.arr) {
				if (child != null) {
					if (dfs(child, pos + 1, word)) {
						return true;
					}
				}
			}
		} else {
			int idx = c - 'a';
			TrieNode child = root.arr[idx];
			if (child != null) {
				return dfs(child, pos + 1, word);
			} 
		}
		return false;
	}
}
