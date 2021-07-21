package bitManipulation;

import java.util.HashMap;
import java.util.Map;

/*
Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.

        Example 1:
        Input: s = "eleetminicoworoep"
        Output: 13
        Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.

        Example 2:
        Input: s = "leetcodeisgreat"
        Output: 5
        Explanation: The longest substring is "leetc" which contains two e's.

        Example 3:
        Input: s = "bcbcbc"
        Output: 6
        Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.

        Constraints:
        1 <= s.length <= 5 x 10^5
        s contains only lowercase English letters.
*/
public class FindTheLongestSubstringContainingVowelsInEvenCounts {
    public int findTheLongestSubstring(String s) {
        Map<Character, Integer> vowelToBitMask = new HashMap<>();
        vowelToBitMask.put('a', 1); //00001 : uoiea
        vowelToBitMask.put('e', 2); //00010 : uoiea
        vowelToBitMask.put('i', 4); //00100 : uoiea
        vowelToBitMask.put('o', 8); //01000 : uoiea
        vowelToBitMask.put('u', 16); //10000 : uoiea
        Map<Integer, Integer> stateToIdx = new HashMap<>();
        stateToIdx.put(0, -1);
        int maxLen = 0, state = 0, len = s.length();
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            if(vowelToBitMask.containsKey(c)){
                int bitToFlip = vowelToBitMask.get(c);
                state ^= bitToFlip;
            }
            stateToIdx.putIfAbsent(state, i);
            maxLen = Math.max(maxLen, i - stateToIdx.get(state));
        }
        return maxLen;
    }
}
