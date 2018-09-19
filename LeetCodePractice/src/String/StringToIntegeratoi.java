package String;

public class StringToIntegeratoi {
	public int atoi(String str) {
		if (str.length() == 0 || str == null) {
			return 0;
		}
		str = str.trim();
		char flag = '+';

		int i = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		double result = 0;

		while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			result = 10 * result + (str.charAt(i) - '0');
			i++;
		}
		if (flag == '-') {
			result = -result;
		}
		if (result > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		if (result < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}

		return (int) result;
	}
}
