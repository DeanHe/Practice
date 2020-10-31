package BitManipulation;

import java.util.*;

/*
With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
word contains the first letter of puzzle.
For each letter in word, that letter is in puzzle.
For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage"; while invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).
Return an array answer, where answer[i] is the number of words in the given word list words that are valid with respect to the puzzle puzzles[i].
 

Example :

Input: 
words = ["aaaa","asas","able","ability","actt","actor","access"], 
puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
Output: [1,1,3,2,4,0]
Explanation:
1 valid word for "aboveyz" : "aaaa" 
1 valid word for "abrodyz" : "aaaa"
3 valid words for "abslute" : "aaaa", "asas", "able"
2 valid words for "absoryz" : "aaaa", "asas"
4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
There're no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.
 

Constraints:

1 <= words.length <= 10^5
4 <= words[i].length <= 50
1 <= puzzles.length <= 10^4
puzzles[i].length == 7
words[i][j], puzzles[i][j] are English lowercase letters.
Each puzzles[i] doesn't contain repeated characters.

analysis:
use bitmask to represent each word word_bitmask, build map of word_bitmask : count
use bitmask to represent each puzzle puzzle_bitmask, iterate each valid morph of puzzle_bitmask, check against freqMap

tag:bitmask
*/
public class NumberOfValidWordsForEachPuzzle {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> wordMaskFreq = new HashMap<>(); // bitmask : count
        for (String w : words) {
            int mask = 0;
            for (char c : w.toCharArray()) {
                mask |= 1 << (c - 'a');
            }
            wordMaskFreq.put(mask, wordMaskFreq.getOrDefault(mask, 0) + 1);
        }
        for (String puzzle : puzzles) {
            int puzzleMask = 0;
            for (char c : puzzle.toCharArray()) {
                puzzleMask |= 1 << (c - 'a');
            }
            int cnt = 0;
            int candidate = puzzleMask;
            int first = 1 << (puzzle.charAt(0) - 'a');
            while (candidate > 0) {
                if ((candidate & first) == first && wordMaskFreq.containsKey(candidate)) {
                    cnt += wordMaskFreq.get(candidate);
                }
                candidate = (candidate - 1) & puzzleMask; // reset the lowest bit to get next lower candidate
            }
            res.add(cnt);
        }
        return res;
    }
}
