package trie;

/*
Implement the StreamChecker class as follows:

StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.
 
Example:
StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist

Note:
1 <= words.length <= 2000
1 <= words[i].length <= 2000
Words will only consist of lowercase English letters.
Queries will only consist of lowercase English letters.
The number of queries is at most 40000.
*/
public class StreamOfCharacters {

	TrieNode root;
	StringBuilder buffer;

	public StreamOfCharacters(String[] words) {
		root = new TrieNode();
		buffer = new StringBuilder();
		for (String word : words) {
			reverseInsert(word);
		}
	}

	public boolean query(char letter) {
		buffer.append(letter);
		TrieNode cur = root;
		for (int i = buffer.length() - 1; i >= 0; i--) {
			char c = buffer.charAt(i);
			int idx = c - 'a';
			if (cur.arr[idx] == null) {
				return false;
			}
			cur = cur.arr[idx];
			if (cur.isEnd) {
				return true;
			}
		}
		return false;
	}

	private void reverseInsert(String word) {
		int len = word.length();
		TrieNode cur = root;
		for (int i = len - 1; i >= 0; i--) {
			int idx = word.charAt(i) - 'a';
			if (cur.arr[idx] == null) {
				cur.arr[idx] = new TrieNode();
			}
			cur = cur.arr[idx];
		}
		cur.isEnd = true;
	}
}
/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words); boolean param_1 =
 * obj.query(letter);
 */
