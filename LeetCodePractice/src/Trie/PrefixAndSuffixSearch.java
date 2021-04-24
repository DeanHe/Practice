package Trie;
/*Given many words, words[i] has weight i.

Design a class WordFilter that supports one function, WordFilter.f(string prefix, string suffix). It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

Examples:

Input:
WordFilter(["apple"])
WordFilter.f("a", "e") // returns 0
WordFilter.f("b", "") // returns -1
 

Note:

words has length in range [1, 15000].
For each test case, up to words.length queries WordFilter.f may be made.
words[i] has length in range [1, 10].
prefix, suffix have lengths in range [0, 10].
words[i] and prefix, suffix queries consist of lowercase letters only.*/

public class PrefixAndSuffixSearch {
	class WordFilter {
		TrieNode root;

		public WordFilter(String[] words) {
			root = new TrieNode();
			int size = words.length;
			for (int weight = 0; weight < size; weight++) {
				String word = words[weight] + "{";
				int len = word.length();
				for (int i = 0; i <= len; i++) {
					TrieNode cur = root;
					cur.weight = weight;
					for (int j = i; j < 2 * len - 1; j++) {
						int idx = word.charAt(j % len) - 'a';
						if (cur.arr[idx] == null) {
							cur.arr[idx] = new TrieNode();
						}
						cur = cur.arr[idx];
						cur.weight = weight;
					}
				}
			}
		}

		public int f(String prefix, String suffix) {
			TrieNode cur = root;
			for (char c : (suffix + "{" + prefix).toCharArray()) {
				if (cur.arr[c - 'a'] == null) {
					return -1;
				}
				cur = cur.arr[c - 'a'];
			}
			return cur.weight;
		}
	}

	/**
	 * Your WordFilter object will be instantiated and called as such:
	 * WordFilter obj = new WordFilter(words); int param_1 =
	 * obj.f(prefix,suffix);
	 */

	class TrieNode {
		TrieNode[] arr;
		int weight;

		public TrieNode() {
			arr = new TrieNode[27]; // 'a' - 'z' and '{'. 'z' and '{' are
									// neighbours in ASCII table
			weight = 0;
		}
	}
}
