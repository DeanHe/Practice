package Backtracking;
/*Given a digit string excluded '0' and '1', return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.

1	
2ABC	
3DEF
4GHI	
5JKL	
6MNO
7PQRS	
8TUV	
9WXYZ
Example
Example 1:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
Explanation: 
'2' could be 'a', 'b' or 'c'
'3' could be 'd', 'e' or 'f'
Example 2:

Input: "5"
Output: ["j", "k", "l"]*/
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
