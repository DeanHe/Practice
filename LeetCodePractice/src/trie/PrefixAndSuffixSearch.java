package trie;
/*
Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix and the suffix.
If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.

Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 

Constraints:

1 <= words.length <= 15000
1 <= words[i].length <= 10
1 <= prefix.length, suffix.length <= 10
words[i], prefix and suffix consist of lower-case English letters only.
At most 15000 calls will be made to the function f.

hint:
For a word like "test", consider "#test", "t#test", "st#test", "est#test", "test#test".
Then if we have a query like prefix = "te", suffix = "t", we can find it by searching for something we've inserted starting with "t#te".

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
				String w = words[idx];
				String pattern = w + "{" + w;
				for (int i = 0; i <= w.length(); i++) {
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
