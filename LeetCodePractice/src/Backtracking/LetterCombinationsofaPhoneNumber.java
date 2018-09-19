package Backtracking;

import java.util.*;

public class LetterCombinationsofaPhoneNumber {
	public List<String> letterCombinations(String digits) {
		String[] matches = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		StringBuilder temp = new StringBuilder();
		List<String> res = new ArrayList<String>();
		appendDigits(digits, 0, matches, temp, res);
		return res;

	}

	public void appendDigits(String digits, int i, String[] matches, StringBuilder temp, List<String> res) {
		if (i == digits.length()) {
			res.add(temp.toString());
			return;
		}

		String letters = matches[digits.charAt(i) - '2'];
		for (int j = 0; j < letters.length(); j++) {
			temp.append(letters.charAt(j));
			appendDigits(digits, i + 1, matches, temp, res);
			temp.deleteCharAt(temp.length() - 1);
		}
	}
}
