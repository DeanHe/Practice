package String;

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

	public String getSay(String string) {
		StringBuilder temp = new StringBuilder();
		int count = 1, len = string.length();
		for (int i = 0; i < len; i++) {
			if (i + 1 < len
					&& string.charAt(i) == string.charAt(i + 1)) {
				count++;
			} else {
				temp.append(count).append(string.charAt(i));
				count = 1;
			}
		}
		return temp.toString();
	}
}
