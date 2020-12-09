package tiktok;

import java.util.TreeMap;

/*
A character transformation T(char1, char2) of a string is defined as converting all char1's
within that string into char2's.
For example, T('t', 's') for string "tiktok" will result in "siksok" by replacing all letter 't' with
letter 's'.
Now you are given 2 strings, named source and target, of equal length. Both of them
consist of lowe rcase characters only.
Find the minimum number of character transformations needed in order to convert
source to target.
If the conversion from source to target is not possible, return 1 instead.

Input Constraints.
• 1 <= length(source) = length(target) < <= 25, (they won't contain all 26 lowercase
characters).

Example 1
Input: source = "aaa", target = "bbb"
Result: 1
// Explanation: only 1 character transformation from letter 'a' to 'b' is needed.

Example 2
Input: source = "ababcc", target = "cccccc"
Result: 2
// Explanation: 2 character transformations are needed .
// T1('a' => 'b'): "ababcc" => "bbbbcc"
// T2('b' => 'c'): "bbbbcc" => "ccсссс"

Example 3
Input: source = "ab", target = "ba"
Result: 3
// Explanation: You cannot directly do 'a' => 'b', which will transform "ab" to "bb"
// T1('a' => 'c'): "ab" => "cb"
// T2('b' => 'a'): "cb" => "ca"
// T3('c' => 'b'): "ca" => "ba"

Example 4
Input: source = 'abac', target =' wxyz '
Result:-1
 */
public class MinimumCharacterTransformation {
    TreeMap<Character, Character> map = new TreeMap<>();

    public int minCovert(String source, String target) {
        int len = source.length();
        int step = 0;
        for (int i = 0; i < len; i++) {
            char s = source.charAt(i);
            char t = target.charAt(i);
            if (s != t) {
                if (map.containsKey(s)) {
                    if (map.get(s) != t) {
                        return -1;
                    }
                } else {
                    map.put(s, t);
                }
            }
        }
        while (!map.isEmpty()) {
            char c = map.firstKey();
            if (isCircle(map, c)) {
                step ++;
            }
            step++;
            map.remove(c);
        }
        return step;
    }

    private boolean isCircle(TreeMap<Character, Character> map, char start) {
        char cur = map.get(start);
        while (map.containsKey(cur)) {
            cur = map.get(cur);
            if(cur == start){
                return true;
            }
        }
        return false;
    }
}
