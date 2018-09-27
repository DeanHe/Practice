package String;

public class MultiplyStrings {
	public String multiply(String num1, String num2) {
		int len1 = num1.length();
		int len2 = num2.length();
		int[] temp = new int[len1 + len2];
		for (int i = len1 - 1; i >= 0; i--) {
			int a = num1.charAt(i) - '0';
			for (int j = len2 - 1; j >= 0; j--) {
				int b = num2.charAt(j) - '0';
				temp[i + j + 1] += a * b;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = temp.length - 1; i >= 0; i--) {
			int digit = temp[i] % 10;
			sb.insert(0, digit);
			int carry = temp[i] / 10;
			if (i > 0) {
				temp[i - 1] += carry;
			}
		}
		while (sb.length() > 0 && sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}
		if (sb.length() == 0) {
			return "0";
		} else {
			return sb.toString();
		}	
	}
}
