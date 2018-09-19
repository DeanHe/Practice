package String;

public class ZigZagConversion {
	public String convert(String s, int nRows) {
        if (s.length() == 0 || s == null || nRows < 1) {
			return "";
		}
		if (nRows == 1) {
			return s;
		}
		StringBuilder temp = new StringBuilder();
		int zigSize = 2 * nRows - 2;
		for (int i = 0; i < nRows; i++) {
			for (int j = i; j < s.length(); j += zigSize) {
				temp.append(s.charAt(j));
				if (i != 0 && i != nRows - 1
						&& j + zigSize - 2 * i < s.length()) {
					temp.append(s.charAt(j + zigSize - 2 * i));
				}
			}
		}
		return temp.toString();
    }
}
