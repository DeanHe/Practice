package Palindrome;
/*Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Follow up:

Coud you solve it without converting the integer to a string?*/
public class PalindromeNumber {
	public boolean isPalindrome(int x) {
        if (x < 0) {
			return false;
		}
		int highest = 1;
		while (x / highest >= 10) {
			highest *= 10;
		}

		while (x != 0) {
			int right = x % 10;
			int left = x / highest;
			if (right == left) {
				x = x % highest;
				x = x / 10;
				highest = highest / 100;
			} else {
				return false;
			}
		}
		return true;
    }
}
