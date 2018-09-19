package String;

import java.util.Arrays;
import java.util.Comparator;

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		String prefix = "";
		Arrays.sort(strs, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});
		if (strs.length != 0) {
			prefix = strs[0];
			for (String s : strs) {
				if (!s.startsWith(prefix)) {
					// find the shorter length of prefix
					StringBuilder temp = new StringBuilder();
					for (int i = 0; i < prefix.length(); i++) {
						if (s.charAt(i) == prefix.charAt(i)) {
							temp.append(prefix.charAt(i));
						} else {
							break;
						}
					}
					prefix = temp.toString();
				}
			}
		}
		return prefix;
	}
}
