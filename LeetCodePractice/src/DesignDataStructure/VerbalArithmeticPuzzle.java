package DesignDataStructure;

import java.util.HashSet;
import java.util.Set;

/*Given an equation, represented by words on left side and the result on right side.

        You need to check if the equation is solvable under the following rules:

        Each character is decoded as one digit (0 - 9).
        Every pair of different characters they must map to different digits.
        Each words[i] and result are decoded as one number without leading zeros.
        Sum of numbers on left side (words) will equal to the number on right side (result).
        Return True if the equation is solvable otherwise return False.

        Example 1:

        Input: words = ["SEND","MORE"], result = "MONEY"
        Output: true
        Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
        Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
        Example 2:

        Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
        Output: true
        Explanation: Map 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
        Such that: "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214
        Example 3:

        Input: words = ["THIS","IS","TOO"], result = "FUNNY"
        Output: true
        Example 4:

        Input: words = ["LEET","CODE"], result = "POINT"
        Output: false


        Constraints:

        2 <= words.length <= 5
        1 <= words[i].length, results.length <= 7
        words[i], result contains only upper case English letters.
        Number of different characters used on the expression is at most 10.
*/
public class VerbalArithmeticPuzzle {
    public boolean isSolvable(String[] words, String result) {
        Set<Character> charSet = new HashSet<>();
        int[] charCnt = new int[26];
        boolean[] nonZeroChar = new boolean[26];
        // parse words
        for (String word : words) {
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];
                charSet.add(c);
                int idx = c - 'A';
                if (i == 0) {
                    nonZeroChar[idx] = true;
                }
                charCnt[idx] += (int) Math.pow(10, arr.length - 1 - i);
            }
        }
        // parse result
        char[] arr = result.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            charSet.add(c);
            int idx = c - 'A';
            if (i == 0) {
                nonZeroChar[idx] = true;
            }
            charCnt[idx] -= (int) Math.pow(10, arr.length - 1 - i);
        }
        //sort charSet to array
        char[] allChar = new char[charSet.size()];
        int i = 0;
        for (char c : charSet) {
            allChar[i++] = c;
        }
        boolean[] usedDigit = new boolean[10];
        return dfs(allChar, charCnt, nonZeroChar, usedDigit, 0, 0);
    }

    private boolean dfs(char[] allChar, int[] charCnt, boolean[] nonZeroChar, boolean[] usedDigit, int step, int sum) {
        if (step == allChar.length) {
            return sum == 0;
        }
        char c = allChar[step];
        int idx = c - 'A';
        for (int i = 0; i <= 9; i++) {
            if (i == 0 && nonZeroChar[idx]) {
                continue;
            }
            if (!usedDigit[i]) {
                usedDigit[i] = true;
                if (dfs(allChar, charCnt, nonZeroChar, usedDigit, step + 1, sum + i * charCnt[idx])) {
                    return true;
                }
                usedDigit[i] = false;
            }
        }
        return false;
    }
}
