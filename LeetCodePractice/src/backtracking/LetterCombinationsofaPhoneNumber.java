package backtracking;

import java.util.*;
/*
Given a digit string excluded '0' and '1', return all possible letter combinations that the number could represent.
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
Output: ["j", "k", "l"]
*/

public class LetterCombinationsofaPhoneNumber {
	String[] matches = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
	public List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<>();
		if(digits == null || digits.length() == 0){
		    return res;
		}
		appendDigits(digits,"", res);
		return res;

	}

	public void appendDigits(String digits, String cur, List<String> res) {
		if (cur.length() == digits.length()) {
			res.add(cur);
			return;
		}
		int i = cur.length();
		String letters = matches[digits.charAt(i) - '2'];
		for (char c : letters.toCharArray()) {
			appendDigits(digits,cur + c, res);
		}
	}
}
