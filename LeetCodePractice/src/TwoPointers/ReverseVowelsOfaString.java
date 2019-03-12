package TwoPointers;
/*Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"
Example 2:

Input: "leetcode"
Output: "leotcede"
Note:
The vowels does not include the letter "y".*/

import java.util.*;

public class ReverseVowelsOfaString {
	public String reverseVowels(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		HashSet<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
		int len = s.length();
		char[] arr = s.toCharArray();
		int start = 0, end = len - 1;
		while (start < end) {
			while (start < end && !set.contains(arr[start])) {
				start++;
			}
			while (start < end && !set.contains(arr[end])) {
				end--;
			}
			char temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
		return new String(arr);
	}

}
