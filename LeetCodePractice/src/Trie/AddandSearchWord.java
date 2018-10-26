package Trie;

public class AddandSearchWord {
	class TrieNode {
		TrieNode[] arr;
		boolean isEnd;

		public TrieNode() {
			arr = new TrieNode[26];
		}
	}

	TrieNode root;

	public AddandSearchWord() {
		root = new TrieNode();
	}

	// Adds a word into the data structure.
	public void addWord(String word) {
		// Write your code here
		TrieNode p = root;
		int len = word.length();
		for (int i = 0; i < len; i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (p.arr[index] != null) {
				p = p.arr[index];
			} else {
				TrieNode temp = new TrieNode();
				p.arr[index] = temp;
				p = p.arr[index];
			}
		}
		p.isEnd = true;
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		// Write your code here
		return dfsSearch(root, 0, word);
	}

	private boolean dfsSearch(TrieNode root, int pos, String word) {
		if (root.isEnd && pos == word.length()) {
			return true;
		}
		if (pos >= word.length()) {
			return false;
		}
		char c = word.charAt(pos);
		if (c == '.') {
			for (int i = 0; i < 26; i++) {
				if (root.arr[i] != null) {
					if (dfsSearch(root.arr[i], pos + 1, word)) {
						return true;
					}
				}
			}
		} else {
			int index = c - 'a';
			if (root.arr[index] != null) {
				return dfsSearch(root.arr[index], pos + 1, word);
			} else {
				return false;
			}
		}
		return false;
	}
}
