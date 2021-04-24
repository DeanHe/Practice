package string;
/*
The count-and-say sequence is the sequence of integers beginning as follows:

1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.

11 is read off as "two 1s" or 21.

21 is read off as "one 2, then one 1" or 1211.

Given an integer n, generate the nth sequence.

Example
Given n = 5, return "111221".

Notice
The sequence of integers will be represented as a string.
*/
public class CountAndSay {
	public String countAndSay(int n) {
		if (n <= 0) {
			return "";
		}

		String result = "1";
		for (int i = 1; i < n; i++) {
			result = getSay(result);
		}
		return result;
	}

	public String getSay(String s) {
		StringBuilder sb = new StringBuilder();
		int count = 1, len = s.length();
		for (int i = 0; i < len; i++) {
			if (i + 1 < len && s.charAt(i) == s.charAt(i + 1)) {
				count++;
			} else {
				sb.append(count).append(s.charAt(i));
				count = 1;
			}
		}
		return sb.toString();
	}
}
