package Trie;

public class TrieNode {
	TrieNode[] arr;
	boolean isEnd;

	public TrieNode() {
		arr = new TrieNode[26];
		isEnd = false;
	}
}
