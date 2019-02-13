package Window;

import java.util.*;

public class KsubstringWithKdifferentCharacters {
	/**
	 * @param stringIn:
	 *            The original string.
	 * @param K:
	 *            The length of substrings.
	 * @return: return the count of substring of length K and exactly K distinct
	 *          characters.
	 */
	public int KSubstring(String stringIn, int K) {
		// Write your code here
		if (stringIn == null || stringIn.length() == 0 || K > stringIn.length()) {
			return 0;
		}
		int len = stringIn.length();
        Set<Character> charSet = new HashSet<>();
        Set<String> stringSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i = 0; i < len; i++){
            char c = stringIn.charAt(i);
            charSet.add(c);
            sb.append(c);
            if(sb.length() == K){
                if(!stringSet.contains(sb.toString()) && charSet.size() == K){
                    stringSet.add(sb.toString());
                    count++;
                }
                if(c != sb.charAt(0)){
                    charSet.remove(sb.charAt(0));
                }
                sb.deleteCharAt(0);
            }
        }
        return count;
	}
}
