package Palindrome;

import java.util.ArrayList;
import java.util.List;

/*Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]*/
public class PalindromePartitioning {
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return res;
		}
		List<String> candidate = new ArrayList<>();
		dfs(res, candidate, s, 0);
		return res;
	}

	private void dfs(List<List<String>> res, List<String> candidate, String s, int pos) {
		int len = s.length();
		if (pos == len) {
			res.add(new ArrayList<>(candidate));
			return;
		}
		for (int i = pos + 1; i <= len; i++) {
			String sub = s.substring(pos, i);
			if (isPalindrome(sub)) {
				candidate.add(sub);
				dfs(res, candidate, s, i);
				candidate.remove(candidate.size() - 1);
			}
		}
	}

	private boolean isPalindrome(String s) {
		int start = 0, end = s.length() - 1;
		while (start < end) {
			if (s.charAt(start) != s.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
}
