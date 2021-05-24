package trie;

import java.util.*;

/*In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:

Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
 

Note:

The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000*/
public class ReplaceWords {
	public String replaceWords(List<String> dict, String sentence) {
		String[] words = sentence.split(" ");
		TrieNode root = buildTrie(dict);
		return replaceWords(root, words);
	}

	private TrieNode buildTrie(List<String> dict) {
		TrieNode root = new TrieNode();
		for (String s : dict) {
			TrieNode cur = root;
			char[] arr = s.toCharArray();
			int len = arr.length;
			for (int i = 0; i < len; i++) {
				char c = arr[i];
				if (cur.arr[c - 'a'] == null) {
					cur.arr[c - 'a'] = new TrieNode();
				}
				cur = cur.arr[c - 'a'];
			}
			cur.isEnd = true;
		}
		return root;
	}

	private String replaceWords(TrieNode root, String[] words) {
		StringBuilder sb = new StringBuilder();
		for (String w : words) {
			sb.append(trim(root, w));
			sb.append(" ");
		}
		return sb.toString().trim();
	}

	private String trim(TrieNode root, String word) {
		StringBuilder sb = new StringBuilder();
		TrieNode cur = root;
		char[] arr = word.toCharArray();
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			char c = arr[i];
			sb.append(c);
			if (cur.arr[c - 'a'] == null) {
				break;
			}
			cur = cur.arr[c - 'a'];
			if (cur.isEnd) {
				return sb.toString();
			}
		}
		return word;
	}
}
