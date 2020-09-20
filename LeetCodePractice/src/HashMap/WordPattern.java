package HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:

Input: pattern = "abba", str = "dog cat cat dog"
Output: true
Example 2:

Input:pattern = "abba", str = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false
Example 4:

Input: pattern = "abba", str = "dog dog dog dog"
Output: false
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String[] strArr = str.split(" ");
        char[] patternArr = pattern.toCharArray();
        if (patternArr.length != strArr.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < strArr.length; i++) {
            char c = patternArr[i];
            String s = strArr[i];
            if (map.containsKey(c)) {
                if (!s.equals(map.get(c))) {
                    return false;
                }
            } else {
                if (visited.contains(s)) {
                    return false;
                }
                map.put(c, s);
                visited.add(s);
            }
        }
        return true;
    }
}
