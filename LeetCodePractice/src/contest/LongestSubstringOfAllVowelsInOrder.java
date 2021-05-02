package contest;

import java.util.HashMap;
import java.util.Map;

/*
A string is considered beautiful if it satisfies the following conditions:

Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear at least once in it.
The letters must be sorted in alphabetical order (i.e. all 'a's before 'e's, all 'e's before 'i's, etc.).
For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful, but "uaeio", "aeoiu", and "aaaeeeooo" are not beautiful.

Given a string word consisting of English vowels, return the length of the longest beautiful substring of word. If no such substring exists, return 0.

A substring is a contiguous sequence of characters in a string.



Example 1:

Input: word = "aeiaaioaaaaeiiiiouuuooaauuaeiu"
Output: 13
Explanation: The longest beautiful substring in word is "aaaaeiiiiouuu" of length 13.
Example 2:

Input: word = "aeeeiiiioooauuuaeiou"
Output: 5
Explanation: The longest beautiful substring in word is "aeiou" of length 5.
Example 3:

Input: word = "a"
Output: 0
Explanation: There is no beautiful substring, so return 0.


Constraints:

1 <= word.length <= 5 * 10^5
word consists of characters 'a', 'e', 'i', 'o', and 'u'.
 */
public class LongestSubstringOfAllVowelsInOrder {
    public int longestBeautifulSubstring(String word) {
        Map<Character, Character> next = new HashMap<>();
        next.put('a', 'e');
        next.put('e', 'i');
        next.put('i', 'o');
        next.put('o', 'u');
        StringBuilder sb = new StringBuilder();
        int res = 0;
        char[] arr = word.toCharArray();
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 'a'){
                if(sb.length() > 0){
                    char last = sb.charAt(sb.length() - 1);
                    if(last != 'a'){
                        sb.setLength(0);
                    }
                }
                sb.append(arr[i]);
            } else {
                if(sb.length() > 0){
                    char last = sb.charAt(sb.length() - 1);
                    if(arr[i] == last ||(next.containsKey(last) && arr[i] == next.get(last))){
                        sb.append(arr[i]);
                    } else {
                        sb.setLength(0);
                    }
                }
            }
            if(sb.length() > 0){
                char last = sb.charAt(sb.length() - 1);
                if(last == 'u'){
                    res = Math.max(res, sb.length());
                }
            }
        }
        return res;
    }
}

