package string;

import java.util.HashMap;
import java.util.Map;

/*
Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.

Return true if and only if you can transform str1 into str2.



Example 1:

Input: str1 = "aabcc", str2 = "ccdee"
Output: true
Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
Example 2:

Input: str1 = "leetcode", str2 = "codeleet"
Output: false
Explanation: There is no way to transform str1 to str2.


Note:

1 <= str1.length == str2.length <= 10^4
Both str1 and str2 contain only lowercase English letters.

analysis:
The same character of str2 must be the same one in str1
Reverse: from str2 to str1

For change of a list, from end to begin. And the end is unsed in str1
For change of a cycle, need a temporary unused character

 Complexity: Time O(n), Space O(1)
*/
public class StringTransformsIntoAnotherString {
    public boolean canConvert(String src, String target){
        if(src.equals(target)){
            return true;
        }
        if(src.length() != target.length()){
            return false;
        }
        int len = src.length();
        Map<Character, Character> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            char c1 = src.charAt(i);
            char c2 = target.charAt(i);
            if(map.containsKey(c1)){
                if(map.get(c1) != c2){
                    return false;
                }
            } else {
                map.put(c1, c2);
            }
        }
        return map.values().stream().distinct().count() < 26;
    }
}
