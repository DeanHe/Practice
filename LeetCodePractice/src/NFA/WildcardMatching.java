package NFA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false
*/
public class WildcardMatching {
    /**
     * @param s: A string
     * @param p: A string includes "?" and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        int pLen = p.length();
        int pState = 0;
        // step 1: build a FSM based on p: 1) state 2) transition
        char[] pArr = p.toCharArray();
        int[][] transition = new int[pLen][256];
        for (int[] arr : transition) {
            Arrays.fill(arr, -1);
        }
        for (char c : pArr) {
            if (c == '*') {
                transition[pState][c] = pState;
            } else {
                transition[pState][c] = pState + 1;
                pState += 1;
            }
        }
        int finalState = pState;
        //step 2: start running the FSM on s
        Set<Integer> sStates = new HashSet<>();
        Set<Integer> temp = new HashSet<>();
        sStates.add(0);
        char[] sArr = s.toCharArray();
        for (char c : sArr) {
            temp.clear();
            for (Integer sState : sStates) {
                char[] tokens = {c, '*', '.'};
                for (char token : tokens) {
                    if(sState < pLen && transition[sState][token] != -1){
                        int nextState = transition[sState][token];
                        temp.add(nextState);
                    }
                }
            }
            sStates.clear();
            sStates.addAll(temp);
        }
        return sStates.contains(finalState);
    }
}
