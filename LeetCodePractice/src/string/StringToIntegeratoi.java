package string;

public class StringToIntegeratoi {
	public int atoi(String str) {
		if (str.length() == 0 || str == null) {
			return 0;
		}
		str = str.trim();
		int i = 0, sign = 1, res = 0;
		if (i < str.length() && (str.charAt(i) == '-' || str.charAt(i) == '+')) {
			sign =  str.charAt(i) == '+' ? 1 : -1;
			i++;
		}

		while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			int digit = str.charAt(i) - '0';
			if(Integer.MAX_VALUE / 10 < res || Integer.MAX_VALUE / 10 == res && Integer.MAX_VALUE % 10 < digit){
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			res = 10 * res + (str.charAt(i) - '0');
			i++;
		}
		return res * sign;
	}
}
