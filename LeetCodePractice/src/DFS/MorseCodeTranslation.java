package DFS;

/*
International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, 
a map is given for example: "a" maps to ".-", "b" maps to "-.", "c" maps to ".-.", and so on.
translate the code to alphabet string.

test:
		Map<String, Character> map = new HashMap<>();
		map.put(".-", 'a');
		map.put("-.", 'b');
		map.put(".-.", 'c');
		MorseCodeTranslation morse = new MorseCodeTranslation();
		String a = morse.translate(".-.-.-.", map);
*/
import java.util.*;

public class MorseCodeTranslation {
	public String translate(String code, Map<String, Character> map) {
		StringBuilder sb = new StringBuilder();
		dfs(code, map, sb);
		return sb.toString();
	}

	private boolean dfs(String code, Map<String, Character> map, StringBuilder sb) {
		int len = code.length();
		if (len == 0) {
			return true;
		}
		for (int i = 1; i <= len; i++) {
			String sub = code.substring(0, i);
			if (map.containsKey(sub)) {
				sb.append(map.get(sub));
				if (dfs(code.substring(i), map, sb)) {
					return true;
				}
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		return false;
	}
}
