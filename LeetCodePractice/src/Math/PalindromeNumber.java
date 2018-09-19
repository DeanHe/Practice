package Math;

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
