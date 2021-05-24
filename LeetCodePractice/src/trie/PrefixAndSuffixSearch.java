package trie;
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
words[i] and prefix, suffix queries consist of lowercase letters only.

analysis
Approach #3: trie of Suffix Wrapped Words
Intuition and Algorithm

Consider the word 'apple'. For each suffix of the word, we could insert that suffix, followed by '#', followed by the word,
all into the trie.

For example, we will insert '#apple', 'e#apple', 'le#apple', 'ple#apple', 'pple#apple', 'apple#apple' into the trie.
Then for a query like prefix = "ap", suffix = "le", we can find it by querying our trie for le#ap.
*/

public class PrefixAndSuffixSearch {
	class WordFilter {
		TrieNode root;

		public WordFilter(String[] words) {
			root = new TrieNode();
			int size = words.length;
			for (int idx = 0; idx < size; idx++) {
				String pattern = words[idx] + "{" + words[idx];
				for (int i = 0; i <= words[idx].length(); i++) {
					TrieNode cur = root;
					cur.idx = idx;
					for (int j = i; j < pattern.length(); j++) {
						int k = pattern.charAt(j) - 'a';
						if (cur.arr[k] == null) {
							cur.arr[k] = new TrieNode();
						}
						cur = cur.arr[k];
						cur.idx = idx;
					}
				}
			}
		}

		public int f(String prefix, String suffix) {
			TrieNode cur = root;
			String pattern = suffix + "{" + prefix;
			for (char c : pattern.toCharArray()) {
				if (cur.arr[c - 'a'] == null) {
					return -1;
				}
				cur = cur.arr[c - 'a'];
			}
			return cur.idx;
		}
	}

	/**
	 * Your WordFilter object will be instantiated and called as such:
	 * WordFilter obj = new WordFilter(words); int param_1 =
	 * obj.f(prefix,suffix);
	 */

	class TrieNode {
		TrieNode[] arr;
		int idx;

		public TrieNode() {
			arr = new TrieNode[27]; // 'a' - 'z' and '{'. 'z' and '{' are
									// neighbours in ASCII table
			idx = 0;
		}
	}
}
