package Greedy;
/*Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring with repeated characters.

Example 1:

Input: text = "ababa"
Output: 3
Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa", which its length is 3.
Example 2:

Input: text = "aaabaaa"
Output: 6
Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.
Example 3:

Input: text = "aaabbaaa"
Output: 4
Example 4:

Input: text = "aaaaa"
Output: 5
Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
Example 5:

Input: text = "abcdef"
Output: 1

Constraints:

1 <= text.length <= 20000
text consist of lowercase English characters only.
*/

public class SwapForLongestRepeatedCharacterSubstring {
	public int maxRepOpt1(String text) {
		int[] freq = new int[26];
		char[] arr = text.toCharArray();
		int len = arr.length;
		int res = 0;
		for (char c : arr) {
			freq[c - 'a']++;
		}
		for (int i = 0; i < len; i++) {
			char cur = arr[i];
			int j = i, cnt = 0, diff = 0;
			while (j < len && (arr[j] == cur || diff == 0) && cnt < freq[cur - 'a']) {
				if (arr[j] != cur) {
					diff++;
				}
				cnt++;
				j++;
			}
			res = Math.max(res, cnt);
		}
		for (int i = len - 1; i >= 0; i--) {
			char cur = arr[i];
			int j = i, cnt = 0, diff = 0;
			while (j >= 0 && (arr[j] == cur || diff == 0) && cnt < freq[cur - 'a']) {
				if (arr[j] != cur) {
					diff++;
				}
				cnt++;
				j--;
			}
			res = Math.max(res, cnt);
		}
		return res;
	}
}
