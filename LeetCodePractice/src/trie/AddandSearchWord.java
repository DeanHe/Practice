package trie;

/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True


Constraints:
1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.
*/
public class AddandSearchWord {
	TrieNode root;

	public AddandSearchWord() {
		root = new TrieNode();
	}

	// Adds a word into the data structure.
	public void addWord(String word) {
		// Write your code here
		TrieNode cur = root;
		for (char c : word.toCharArray()) {
			int idx = c - 'a';
			if (cur.arr[idx] == null) {
				cur.arr[idx] = new TrieNode();
			}
			cur = cur.arr[idx];
		}
		cur.isEnd = true;
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
