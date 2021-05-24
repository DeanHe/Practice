package trie;

public class Trie {
	TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public void insert(String word) {
		TrieNode p = root;
		int len = word.length();
		for (int i = 0; i < len; i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (p.arr[index] == null) {
				p.arr[index] = new TrieNode();
			}
			p = p.arr[index];
		}
		p.isEnd = true;
	}

	public boolean search(String word) {
		TrieNode p = root;
		int len = word.length();
		for (int i = 0; i < len; i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (p.arr[index] == null) {
				return false;
			}
			p = p.arr[index];
		}
		if (p == root) {
			return false;
		}
		return p.isEnd;
	}

	public boolean startWith(String word) {
		TrieNode p = root;
		int len = word.length();
		for (int i = 0; i < len; i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (p.arr[index] == null) {
				return false;
			}
			p = p.arr[index];
		}
		if (p == root) {
			return false;
		}
		return p != null;
	}
}
